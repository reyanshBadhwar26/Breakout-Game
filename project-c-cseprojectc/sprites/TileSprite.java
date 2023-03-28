import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileSprite implements DisplayableSprite, TileSprites {

	private Image tile;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 0;
	private double height = 0;
	private boolean dispose = false;
	private String color = "";
	private String tileName = "";

	public TileSprite(double centerX, double centerY, String tileName, String color) {
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

		this.tileName = tileName;
		this.color = color;
	}

	@Override
	public Image getImage() {
		return tile;
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

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {

	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getTileName() {
		return tileName;
	}

}
