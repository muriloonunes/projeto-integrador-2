package hmd.teatroABC.model.entities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 19/11/2024
 * @brief Class Peca
 */
public class Peca {
    private Sessao sessao;
    private String nome;

    ArrayList<Boolean> assentos = new ArrayList<>();
    private File poster;
    private Image posterImg;

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
