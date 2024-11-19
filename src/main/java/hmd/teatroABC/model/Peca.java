package hmd.teatroABC.model;

import javafx.scene.image.Image;

import java.io.File;

/**
 * @author Murilo Nunes <murilo_no@outlook.com>
 * @date 19/11/2024
 * @brief Class Peca
 */
public class Peca {
    File poster;
    Image posterImg;

    public Peca(File poster) {
        this.poster = poster;
        configurarPoster();
    }

    public File getPoster() {
        return poster;
    }

    public void setPoster(File poster) {
        this.poster = poster;
    }

    public Image getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(Image posterImg) {
        this.posterImg = posterImg;
    }

    public void configurarPoster() {
        this.posterImg = new Image(poster.toURI().toString());
    }
}
