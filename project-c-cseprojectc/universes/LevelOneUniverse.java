import java.util.ArrayList;
import java.util.Random;

public class LevelOneUniverse implements Universe {

	private boolean complete = false;
	private DisplayableSprite player1 = null;
	private DisplayableSprite ball = null;
	private DisplayableSprite pinkTile = null;
	private DisplayableSprite blueTile = null;
	private DisplayableSprite greenTile = null;
	private DisplayableSprite orangeTile = null;
	private DisplayableSprite coinSprite = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private ArrayList<DisplayableSprite> spritesWithoutTile = new ArrayList<DisplayableSprite>();
	ArrayList<DisplayableSprite> lowerBarriers = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> tileSprites = new ArrayList<DisplayableSprite>();
	private Background background;
	private int lives = 5;
	private int score = 0;
	Random rand = new Random();

	public final double TILE_START_POINT = 88;
	public final double TILE_STOP_POINT = 775;
	public final double TILE_WIDTH = 75;

	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();

	public LevelOneUniverse() {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new PaddleSprite(425, 550);
		sprites.add(player1);
		spritesWithoutTile.add(player1);
		ball = new BallSprite(425, 530, 300, 300, "res/ball.png");
		sprites.add(ball);

		background = new AllLevelsBackground();
		ArrayList<DisplayableSprite> barriers = ((AllLevelsBackground) background).getBarriers();
		lowerBarriers = ((AllLevelsBackground) background).getLowerBarrier();
		sprites.addAll(barriers);
		spritesWithoutTile.addAll(barriers);
		backgrounds = new ArrayList<Background>();
		backgrounds.add(background);

		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i + TILE_WIDTH) {
			pinkTile = new TileSprite(i, 100, "res/pinkTile.png", "pink");
			tileSprites.add(pinkTile);
		}

		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i + TILE_WIDTH) {
			blueTile = new TileSprite(i, 130, "res/blueTile.png", "blue");
			tileSprites.add(blueTile);
		}

		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i + TILE_WIDTH) {
			orangeTile = new TileSprite(i, 160, "res/orangeTile.png", "orange");
			tileSprites.add(orangeTile);
		}

		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i + TILE_WIDTH) {
			greenTile = new TileSprite(i, 190, "res/greenTile.png", "green");
			tileSprites.add(greenTile);
		}

		for (int i = 0; i <= 5; i++) {
			int randomTile = rand.nextInt(tileSprites.size());
			if (tileSprites.get(randomTile) instanceof ExceptionalTileSprite == false) {
				tileSprites.set(randomTile,
						new ExceptionalTileSprite(tileSprites.get(randomTile).getCenterX(),
								tileSprites.get(randomTile).getCenterY(),
								((TileSprites) tileSprites.get(randomTile)).getTileName(),
								((TileSprites) tileSprites.get(randomTile)).getColor(), "coinTile"));
			}

		}

		sprites.addAll(tileSprites);

	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 420;
	}

	public double getYCenter() {
		return 325;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	public void setComplete(boolean complete) {
	}

	public ArrayList<DisplayableSprite> getLowerBarriers() {
		return lowerBarriers;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}

	public boolean levelFinished() {

		boolean returnValue = true;

		for (DisplayableSprite sprite : sprites) {
			if (sprite instanceof TileSprite || sprite instanceof ExceptionalTileSprite || sprite instanceof CoinSprite
					|| sprite instanceof BulletSprite) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}

	public ArrayList<DisplayableSprite> getSpritesWithoutTiles() {
		return spritesWithoutTile;
	}

	public void update(KeyboardInput keyboard, long actual_delta_time) {

		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
		}

		if (ball.getDispose() == true) {

			if (lives != 0) {
				lives -= 1;
			}

			if (lives - 1 >= 0) {
				ball = new BallSprite(player1.getCenterX(), player1.getCenterY() - 50, 300, 300, "res/ball.png");
				sprites.add(ball);
			}
		}

		disposeSprites();

	}

	public String toString() {
		return "";
	}

	protected void disposeSprites() {

		// collect a list of sprites to dispose
		// this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			if (sprite.getDispose() == true) {
				if (sprite instanceof TileSprite || sprite instanceof ExceptionalTileSprite) {
					score += 20;
				}
				if (sprite instanceof ExceptionalTileSprite) {
					coinSprite = new CoinSprite(sprite.getCenterX(), sprite.getCenterY());
					sprites.add(coinSprite);
				}
				if (sprite instanceof CoinSprite) {
					if (((CoinSprite) sprite).getCollisionWithPaddle()) {
						score += 10;
					}
				}
				if (sprite instanceof BulletSprite && lives != 0) {
					if (((BulletSprite) sprite).getCollisionWithPaddle()) {
						lives -= 1;
					}
				}
				disposalList.add(sprite);
			}
		}

		// go through the list of sprites to dispose
		// note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			sprites.remove(sprite);
		}

		// clear disposal list if necessary
		if (disposalList.size() > 0) {
			disposalList.clear();
		}
	}

}
