package lab6_code.zad2;

public class SnailThread extends Thread {
    private boolean isWaiting;

    private float eatAmountPerTick; //v
    private LeafCell currentCell;
    private float cellChangeThreshold = 1;
    private LeafThread leafThread;

    private final int sleepTime = 2000; //t
    private final int updatePeriod = 200; //time between ticks in milliseconds

    /**
     * CTOR
     * @param leafThread
     * @param eatAmountPerTick
     * @param currentCell
     */
    public SnailThread(LeafThread leafThread, float eatAmountPerTick, LeafCell currentCell) {
        super();

        this.leafThread = leafThread;
        this.eatAmountPerTick = eatAmountPerTick;
        this.currentCell = currentCell;
        isWaiting = false;
    }

    /**
     * Will set snail's current cell
     * @param cell
     */
    public void SetCurrentCell(LeafCell cell)
    {
        this.currentCell = cell;
    }

    /**
     * Will call DoLogic function each tick
     */
    private void Update()
    {
        while (true) {
            while (isWaiting) {
                synchronized(SnailThread.this)
                {
                    System.out.println(currentCell.GetFoodAmount());
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("Has woken");
            }

            currentCell.ChangeFoodAmount(-eatAmountPerTick);
            DoLogic();

            if(currentCell != null)
            {
                if(currentCell.IsSnail() == false)
                {
                    System.out.println("Error: " + currentCell.GetMapPosition());
                }
            }

            try {
                Thread.sleep(updatePeriod);
            } catch (InterruptedException e) {
            }
        }
        
    }

    /**
     * Snail logic:
     * Snail will eat food from leaf cell he is currently on.
     * Will change cell if food amount of any of neighbor cells is:
     *                  - Grater than 0
     *                  - Grater than food amount of current cell by cellChangeThreshold param
     * Will put snail to sleep if there is no food on current cell and neighbors cells
     */
    private void DoLogic()
    {
        var nigh = currentCell.GetNeighbors();
        LeafCell next = null;
        for (var ce : nigh) {
            if(ce.GetFoodAmount() == 0) continue;
            if(ce.IsSnail()) continue;
            if(next == null){
                next = ce;
                continue;
            }
            if(ce.GetFoodAmount() > next.GetFoodAmount())
            {
                next = ce;
            }
        }

        if(next !=null && currentCell.GetFoodAmount() != 0 && next.GetFoodAmount() - currentCell.GetFoodAmount() < cellChangeThreshold)
            next = null;


        if(next != null)
        {
            leafThread.TryMoveSnail(currentCell,next);
        }
        else if(currentCell.GetFoodAmount() ==0 )
        {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void run() {
        super.run();
        Update();
    }
}
