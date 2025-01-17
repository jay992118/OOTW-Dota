package Tower;

import static Tower.LightningTowerFactory.Towerlist;
public class ArcherTowerFactory implements TowerFactory{
    @Override
    public Tower createTower() {
        return new ArcherTower(20, 20, 1, 500);
    }

    public static void addTower(Tower tower, int x, int y) {
        TowerPlacement placement = new TowerPlacement(tower, x, y);
        Towerlist.add(placement);
    }
}
