import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ExceptionalTileSprite implements DisplayableSprite {

	private Image tile;
	private Image brokenBlueTile;
	private Image brokenGreenTile;
	private Image brokenOrangeTile;
	private Image brokenPinkTile;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 0;
	private double height = 0;
	private boolean dispose = false;
	private int lives = 2;
	private String color = "";
	private String type = "";

	public ExceptionalTileSprite(double centerX, double centerY, String tileName, String color, String type) {
		this.centerX = centerX;
		this.centerY = centerY;

		if (tile == null) {
			try {
				tile = ImageIO.read(new File(tileName));
				this.height = 30;
				this.width = 75;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (brokenBlueTile == null) {
			try {
				brokenBlueTile = ImageIO.read(new File("res/brokenBlueTile.png"));
				this.height = 30;
				this.width = 75;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (brokenGreenTile == null) {
			try {
				brokenGreenTile = ImageIO.read(new File("res/brokenGreenTile.png"));
				this.height = 30;
				this.width = 75;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (brokenOrangeTile == null) {
			try {
				brokenOrangeTile = ImageIO.read(new File("res/brokenOrangeTile.png"));
				this.height = 30;
				this.width = 75;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (brokenPinkTile == null) {
			try {
				brokenPinkTile = ImageIO.read(new File("res/brokenPinkTile.png"));
				this.height = 30;
				this.width = 75;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		this.color = color;
		this.type = type;

	}

	@Override
	public Image getImage() {

		Image returnImage = null;

		if (lives < 2 && color.equals("blue")) {
			returnImage = brokenBlueTile;
		} else if (lives < 2 && color.equals("green")) {
			returnImage = brokenGreenTile;
		} else if (lives < 2 && color.equals("orange")) {
			returnImage = brokenOrangeTile;
		} else if (lives < 2 && color.equals("pink")) {
			returnImage = brokenPinkTile;
		} else {
			returnImage = tile;
		}

		return returnImage;
	}

	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public double getMinX() {
		return centerX - (width / 2);
	}

	@Override
	public double getMaxX() {
		return centerX + (width / 2);
	}

	@Override
	public double getMinY() {
		return centerY - (height / 2);
	}

	@Override
	public double getMaxY() {
		return centerY + (height / 2);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getCenterX() {
		return centerX;
	}

	@Override
	public double getCenterY() {
		return centerY;
	}

	@Override
	public boolean getDispose() {
		return dispose;
	}

	@Override
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public String getType() {
		return type;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {

	}

}
