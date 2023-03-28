import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AllLevelsBackground implements Background {

	protected static int TILE_WIDTH = 50;
	protected static int TILE_HEIGHT = 50;

	private Image blueTile;
	private int maxCols = 0;
	private int maxRows = 0;

	private int map[][] = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },

	};

	public AllLevelsBackground() {
		try {
			this.blueTile = ImageIO.read(new File("res/blue.png"));
		} catch (IOException e) {
			// System.out.println(e.toString());
		}
		maxRows = map.length - 1;
		maxCols = map[0].length - 1;

	}

	@Override
	public Tile getTile(int col, int row) {

		Image image = null;

		if (row < 0 || row > maxRows || col < 0 || col > maxCols || map[row][col] == 1) {
			image = null;
		} else if (map[row][col] == 0 || map[row][col] == 3) {
			image = blueTile;
		}

		int x = (int) ((col * TILE_WIDTH));
		int y = (int) ((row * TILE_HEIGHT));

		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);

		return newTile;
	}

	@Override
	public int getCol(double x) {

		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (int) (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			} else {
				return col;
			}
		} else {
			return 0;
		}

	}

	@Override
	public int getRow(double y) {

		int row = 0;

		if (TILE_HEIGHT != 0) {
			row = (int) (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			} else {
				return row;
			}
		} else {
			return 0;
		}
	}

	@Override
	public double getShiftX() {
		return 0;
	}

	@Override
	public double getShiftY() {
		return 0;
	}

	@Override
	public void setShiftX(double shiftX) {
	}

	@Override
	public void setShiftY(double shiftY) {
	}

	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == 0) {
					barriers.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH,
							(row + 1) * TILE_HEIGHT, false));
				}
			}
		}
		return barriers;
	}

	public ArrayList<DisplayableSprite> getLowerBarrier() {
		ArrayList<DisplayableSprite> lowerBarrier = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == 3) {
					lowerBarrier.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH,
							(row + 1) * TILE_HEIGHT, false));
				}
			}
		}
		return lowerBarrier;
	}
}
