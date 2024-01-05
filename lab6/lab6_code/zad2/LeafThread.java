package lab6_code.zad2;

import java.util.ArrayList;
import java.util.Random;

public class LeafThread extends Thread{
    
    private Vector2Int mapSize;
    private int snailsAmount;
    private ArrayList<LeafCell> mapCells;
    private ArrayList<SnailThread> snails;
    private Random random;

    /* SETUP */
    private final float minFoodAmountOnCellOnBegin = 1;
    private final float maxFoodAmountOnCellOnBegin = 9;
    private final float minSnailEatSpeedPerTick = 0.1f;
    private final float maxSnailEatSpeedPerTick = 0.5f;
    private final int timeBetweenTicks = 5000; //Time between ticks int leafThread (used for incrementing food amount)
    private final float maxFoodAmountAddedPerTick = 4;
    private final float minFoodAmountAddedPerTick = 0.5f;
    /* ----- */

    /**
     * CTOR
     * @param snailsAmount
     * @param mapSize
     */
    public LeafThread(int snailsAmount, Vector2Int mapSize) {
        super();

        this.mapSize = mapSize;
        this.snailsAmount = snailsAmount;

        mapCells = new ArrayList<>();
        snails = new ArrayList<>();
        random = new Random();

        SetupMap();

    }

    /**
     * 
     * @return mapCells param
     */
    public ArrayList<LeafCell> GetMapCells()
    {
        return mapCells;
    }

    /**
     * 
     * @return mapSize param
     */
    public Vector2Int GetMapSize()
    {
        return mapSize;
    }

    /**
     * Will setup map with given random values
     * Then will randomly put needed amount of snails on it
     */
    private void SetupMap()
    {
        for (int i = 0; i < mapSize.GetX(); i++) {
            for (int j = 0; j < mapSize.GetY(); j++) {
                Vector2Int pos = new Vector2Int(i, j);
                float foodAmount = random.nextFloat(minFoodAmountOnCellOnBegin,maxFoodAmountOnCellOnBegin);
                mapCells.add(new LeafCell(pos, foodAmount));
            }
        }

        for (LeafCell outer : mapCells) {
            ArrayList<LeafCell> neigh = new ArrayList<>();
            for (LeafCell inner : mapCells) {
                if(outer == inner) continue;
                if(outer.GetMapPosition().GetNonSquaredDistanceToPoint(inner.GetMapPosition()) <= 2)
                    neigh.add(inner);
            }
            outer.SetNeighbors(neigh);
        }



        var freeCells = new ArrayList<>(mapCells);
        for (int i = 0; i < snailsAmount;i++) {
            var free = freeCells.remove(random.nextInt(0,freeCells.size()));
            var sn = CreateSnail(free);
            free.SetSnail(sn);
        }

        System.out.println("Map set");
    }

    /**
     * Will move snail from snailCell to nextSnailCell if nextSnailCell is not occupied
     * @param snailCell
     * @param nextSnailCell
     */
    public synchronized boolean TryMoveSnail(LeafCell snailCell, LeafCell nextSnailCell)
    {
        if(snailCell.IsSnail() == false) return false;
        if(nextSnailCell ==null || nextSnailCell.IsSnail() == true) return false;
        
        var sn = snailCell.RemoveSnail();
        nextSnailCell.SetSnail(sn);

        return true;
    }

    /**
     * 
     * @param cell
     * @return new snail with radom values
     */
    private SnailThread CreateSnail(LeafCell cell)
    {
        float eatSpeedPerTick = random.nextFloat(minSnailEatSpeedPerTick,maxSnailEatSpeedPerTick);
        SnailThread sn = new SnailThread(this,eatSpeedPerTick, cell);
        snails.add(sn);
        return sn;
    }
    
    /**
     * Will add food to each cell
     */
    private synchronized void IncrementFoodAmountInCells()
    {
        for (LeafCell leafCell : mapCells) {
            leafCell.ChangeFoodAmount(random.nextFloat(minFoodAmountAddedPerTick,maxFoodAmountAddedPerTick));
        }
    }

    /**
     * Tick function, will increment food each tick
     */
    private void Update()
    {
        while (true) {
            try {
                Thread.sleep(timeBetweenTicks);
            } catch (InterruptedException e) {
            }
            IncrementFoodAmountInCells();
            System.out.println("Food added");
        }
    }

    
    @Override
    public void run() {
        super.run();

        for (var sn : snails) {
            sn.start();
        }

        Update();
    }
}
