import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PaddleSprite implements DisplayableSprite {

	private Image paddle;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 0;
	private double height = 0;
	private boolean dispose = false;

	private final double VELOCITY = 500;

	public PaddleSprite(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		if (paddle == null) {
			try {
				paddle = ImageIO.read(new File("res/paddle1.png"));
				this.height = this.paddle.getHeight(null);
				this.width = this.paddle.getWidth(null);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	@Override
	public Image getImage() {
		return paddle;
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

		double velocityX = 0;
		double velocityY = 0;

		double deltaX = actual_delta_time * 0.001 * velocityX;
		double deltaY = actual_delta_time * 0.001 * velocityY;

		if (checkCollisionWithBarrier(universe.getSprites(), deltaX, deltaY) == false) {
			// LEFT ARROW
			if (keyboard.keyDown(37)) {
				velocityX = -VELOCITY;
			}
			// RIGHT ARROW
			if (keyboard.keyDown(39)) {
				velocityX += VELOCITY;
			}
		}

		else {
			if (this.centerX < 425 && keyboard.keyDown(39)) {
				velocityX += VELOCITY;
			}
			if (this.centerX > 425 && keyboard.keyDown(37)) {
				velocityX = -VELOCITY;
			}
		}

		this.centerX += actual_delta_time * 0.001 * velocityX;
		this.centerY += actual_delta_time * 0.001 * velocityY;

	}

	private boolean checkCollisionWithBarrier(ArrayList<DisplayableSprite> sprites, double deltaX, double deltaY) {

		// deltaX and deltaY represent the potential change in position
		boolean colliding = false;

		for (DisplayableSprite sprite : sprites) {
			if (sprite instanceof BarrierSprite) {
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY,
						this.getMaxX() + deltaX, this.getMaxY() + deltaY, sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())) {
					colliding = true;
					break;
				}
			}
		}
		return colliding;
	}

	public double getVelocityX() {
		return 0;
	}

}
