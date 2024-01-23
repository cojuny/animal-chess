
package hk.edu.polyu.comp.comp2021.jungle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import hk.edu.polyu.comp.comp2021.jungle.model.*;

//import javax.swing.Icon;

public class ImageLoader{
    final int PIECENUM = 9;

    BufferedImage backgroundImage, water, grass, waterHighlight, grassHighlight, trap, trapHighlight;
    BufferedImage[][] pieceImg = new BufferedImage[2][PIECENUM];
    BufferedImage[][] pieceImgHighlight = new BufferedImage[2][PIECENUM];

    ImageLoader() {
        try {
            backgroundImage = ImageIO.read(this.getClass().getResource("model/resource/background.jpg"));

            water = ImageIO.read(this.getClass().getResource("model/resource/water.jpg"));
            waterHighlight = ImageIO.read(this.getClass().getResource("model/resource/water_h.jpg"));
            grass = ImageIO.read(this.getClass().getResource("model/resource/grass.jpg"));
            grassHighlight = ImageIO.read(this.getClass().getResource("model/resource/grass_h.jpg"));
            trap = ImageIO.read(this.getClass().getResource("model/resource/trap.jpg"));
            trapHighlight = ImageIO.read(this.getClass().getResource("model/resource/trap_h.jpg"));

            pieceImg[0][0] = ImageIO.read(this.getClass().getResource("model/resource/den_r.jpg"));
            pieceImg[1][0] = ImageIO.read(this.getClass().getResource("model/resource/den_b.jpg"));
            pieceImgHighlight[0][0] = ImageIO.read(this.getClass().getResource("model/resource/den_r_h.jpg"));
            pieceImgHighlight[1][0] = ImageIO.read(this.getClass().getResource("model/resource/den_b_h.jpg"));

            pieceImg[0][1] = ImageIO.read(this.getClass().getResource("model/resource/rat_r.jpg"));
            pieceImg[1][1] = ImageIO.read(this.getClass().getResource("model/resource/rat_b.jpg"));
            pieceImgHighlight[0][1] = ImageIO.read(this.getClass().getResource("model/resource/rat_r_h.jpg"));
            pieceImgHighlight[1][1] = ImageIO.read(this.getClass().getResource("model/resource/rat_b_h.jpg"));

            pieceImg[0][2] = ImageIO.read(this.getClass().getResource("model/resource/cat_r.jpg"));
            pieceImg[1][2] = ImageIO.read(this.getClass().getResource("model/resource/cat_b.jpg"));
            pieceImgHighlight[0][2] = ImageIO.read(this.getClass().getResource("model/resource/cat_r_h.jpg"));
            pieceImgHighlight[1][2] = ImageIO.read(this.getClass().getResource("model/resource/cat_b_h.jpg"));

            pieceImg[0][3] = ImageIO.read(this.getClass().getResource("model/resource/dog_r.jpg"));
            pieceImg[1][3] = ImageIO.read(this.getClass().getResource("model/resource/dog_b.jpg"));
            pieceImgHighlight[0][3] = ImageIO.read(this.getClass().getResource("model/resource/dog_r_h.jpg"));
            pieceImgHighlight[1][3] = ImageIO.read(this.getClass().getResource("model/resource/dog_b_h.jpg"));

            pieceImg[0][4] = ImageIO.read(this.getClass().getResource("model/resource/wolf_r.jpg"));
            pieceImg[1][4] = ImageIO.read(this.getClass().getResource("model/resource/wolf_b.jpg"));
            pieceImgHighlight[0][4] = ImageIO.read(this.getClass().getResource("model/resource/wolf_r_h.jpg"));
            pieceImgHighlight[1][4] = ImageIO.read(this.getClass().getResource("model/resource/wolf_b_h.jpg"));

            pieceImg[0][5] = ImageIO.read(this.getClass().getResource("model/resource/leopard_r.jpg"));
            pieceImg[1][5] = ImageIO.read(this.getClass().getResource("model/resource/leopard_b.jpg"));
            pieceImgHighlight[0][5] = ImageIO.read(this.getClass().getResource("model/resource/leopard_r_h.jpg"));
            pieceImgHighlight[1][5] = ImageIO.read(this.getClass().getResource("model/resource/leopard_b_h.jpg"));

            pieceImg[0][6] = ImageIO.read(this.getClass().getResource("model/resource/tiger_r.jpg"));
            pieceImg[1][6] = ImageIO.read(this.getClass().getResource("model/resource/tiger_b.jpg"));
            pieceImgHighlight[0][6] = ImageIO.read(this.getClass().getResource("model/resource/tiger_r_h.jpg"));
            pieceImgHighlight[1][6] = ImageIO.read(this.getClass().getResource("model/resource/tiger_b_h.jpg"));

            pieceImg[0][7] = ImageIO.read(this.getClass().getResource("model/resource/lion_r.jpg"));
            pieceImg[1][7] = ImageIO.read(this.getClass().getResource("model/resource/lion_b.jpg"));
            pieceImgHighlight[0][7] = ImageIO.read(this.getClass().getResource("model/resource/lion_r_h.jpg"));
            pieceImgHighlight[1][7] = ImageIO.read(this.getClass().getResource("model/resource/lion_b_h.jpg"));

            pieceImg[0][8] = ImageIO.read(this.getClass().getResource("model/resource/elephant_r.jpg"));
            pieceImg[1][8] = ImageIO.read(this.getClass().getResource("model/resource/elephant_b.jpg"));
            pieceImgHighlight[0][8] = ImageIO.read(this.getClass().getResource("model/resource/elephant_r_h.jpg"));
            pieceImgHighlight[1][8] = ImageIO.read(this.getClass().getResource("model/resource/elephant_b_h.jpg"));

        } catch (final Exception e) {
            Utility.error("Error: Display image error.");
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(Piece piece, Location l) {
        if (piece == null) {
            if (Jungle.isWater(l)) {
                return this.water;
            }
            else return this.grass;
        }
        else if (piece instanceof Trap) {
            return this.trap;
        }
        else {
            int team = 0, rank = piece.rank;
            if (piece.team == Team.Away) {
                team = 1;
            }
            return this.pieceImg[team][rank];
        }
    }

    public BufferedImage getImageHighlight(Piece piece, Location l) {
        if (piece == null) {
            if (Jungle.isWater(l)) {
                return this.waterHighlight;
            }
            else return this.grassHighlight;
        }
        else if (piece instanceof Trap) {
            return this.trapHighlight;
        }
        else {
            int team = 0, rank = piece.rank;
            if (piece.team == Team.Away) {
                team = 1;
            }
            return this.pieceImgHighlight[team][rank];
        }
    }


}
