/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitmusic.hmi.modules.playbar;

import bitmusic.hmi.mainwindow.WindowComponent;
import bitmusic.hmi.patterns.AbstractController;
import bitmusic.music.player.BitMusicPlayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javazoom.jl.player.Player;

/**
 *
 * @author unkedeuxke
 */
public final class PlayBarController extends AbstractController<PlayBarModel, PlayBarView> {

    private boolean resume;
    private boolean pause;
    private boolean stop;
    private Thread sliderThread;

    private int frame ;

    public PlayBarController(final PlayBarModel model, final PlayBarView view) {
        super(model, view);

        resume = false;
        pause = false;

        frame = 0;
    }

    public class PlayListener implements ActionListener  {
        final private Path p = Paths.get("ilikeit.mp3");
        final private String fileNameTochange = new File("").getAbsolutePath().toString() + "\\songitems\\ilikeit.mp3";



        // Ici il faut mettre le chamin absolu avec deux back slash sinon il va renvoyer une FileNotFoundException
        final private String fileName = "C:\\Users\\khadre\\Documents\\NetBeansProjects\\BitMusic\\BitMusic\\bitmusic\\hmi\\modules\\playbar\\songitems\\ilikeit.mp3";
        private Player player;
        @Override
        public void actionPerformed(ActionEvent e) {
           // try {
                System.out.println("---- Clic sur le bouton Play");
                String test = new File("").getAbsolutePath().toString();
                System.out.println("----- absolute path = " + test);

                stop = false;
                BitMusicPlayer bitMusic = BitMusicPlayer.getInstance();
                final WindowComponent win = WindowComponent.getInstance();
                // Plays a song
                System.out.println("-----Playing the song for the first time");
                win.getPlayBarComponent().getView().setPlayIcon(win.getPlayBarComponent().getView().getPauseIcon());
                JSlider playBar = win.getPlayBarComponent().getView().getPlayBar();

                win.getApiMusic().playSongFromStart(fileNameTochange);
                /*while(win.getApiMusic().getCurrentFrame() != win.getApiMusic().getNumberOfFrame() ) {
                   playBar.setValue(win.getApiMusic().getCurrentFrame());
                }*/
                System.out.println("--- ApiMusic : number of frames = " + win.getApiMusic().getNumberOfFrame());
                //frame = win.getApiMusic().getCurrentFrame();
                // WE WILL NEED THE FRAME RATE TO PROPERLY ANIMATE THE SLIDER

                Runnable r = new Runnable() {
                    public void run() {
                        sliderUpdater(win);
                    }
                };
                sliderThread = new Thread(r);
                sliderThread.setName("SliderThread");
                sliderThread.setPriority(Thread.MAX_PRIORITY);
                sliderThread.start();

                
        }
    }

    public void sliderUpdater(WindowComponent win) {
        JSlider playBar = win.getPlayBarComponent().getView().getPlayBar();
        playBar.setMaximum(win.getApiMusic().getNumberOfFrame());
        while(stop == false) {
            playBar.setValue(win.getApiMusic().getCurrentFrame());
        }
        playBar.setValue(0);
    }

    public class StopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("---- Clic sur le bouton Stop");
            // Stops or pauses a song that is being played
              // we pause the song
             stop = true;
             WindowComponent win = WindowComponent.getInstance();
             win.getApiMusic().stop();
             sliderThread.interrupt();
        }
    }

    public class DownloadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("---- Clic sur le bouton Download");

            // Downloads the song
        }
    }

    // Pour 'écouter' le temps de lecture du son et l'afficher sur la slider
    // Bonne idée. Mais la prochaine fois, let me know you wrote this ;)
    public class SoundTimeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // TODO
        }

    }
}
