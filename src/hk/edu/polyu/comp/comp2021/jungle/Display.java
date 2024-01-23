package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.model.*;

import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Stack;

public class Display{

    final int GRIDSIZE = 85;
    final JFrame frame = new JFrame("Animal Chess");
    ImageLoader images = new ImageLoader();
    final JButton[][] icons = new JButton[Location.XMAX][Location.YMAX];
    Jungle j;
    final int[] YMAPPER = {8,7,6,5,4,3,2,1,0};
    Stack<JButton> buttonStack = new Stack<JButton>();
    Stack<Location> locationStack = new Stack<Location>();

    // Init **************************************
    public Display() {
        this.j = new Jungle();
        init();
        Canvas bg = new Canvas();
        frame.add(bg);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {
        initIcon();
        enableTurnPiece();
    }

    private void enableTurnPiece() {
        Location l;
        Piece p;
        for(int y = Location.YMAX-1; y >= 0; y--) {
            for(int x = 0; x < Location.XMAX; x++)  {
                l = new Location(x,y);
                p = j.getPiece(l);
                if (p != null) {
                    if (j.isPlayersPiece(p)) {
                        enable(icons[x][y]);
                    }
                    else {
                        disable(icons[x][y]);
                    }
                }
                
            }
        }

    }

    private void initIcon() {
        Location l;
        for(int y = Location.YMAX-1; y >= 0; y--) {
            for(int x = 0; x < Location.XMAX; x++)  {
                l = new Location(x,y);
                icons[x][y] = new JButton(new ImageIcon(images.getImage(j.getPiece(l),l)));
                icons[x][y].setBounds(x*GRIDSIZE,YMAPPER[y]*GRIDSIZE,GRIDSIZE,GRIDSIZE);

                final int X = x, Y = y;
                icons[x][y].addActionListener(e -> doAction(icons[X][Y],new Location(X,Y)));
                frame.add(icons[x][y]);
                disable(icons[x][y]);
            }
        }
    }

    private void doAction(JButton i, Location l) {
        // case one: stack is empty, clicked on valid object
        // case two: stack is not empty, clicked on null object
        // case three: stack is not empty, clicked on valid object
        boolean isRun = false;
        if (buttonStack.isEmpty()) {
            if (j.getPiece(l) == null) {
                return; // error
            }

            else if (j.getPiece(l) != null) {
                for (Location move: j.getPiece(l).getMovables()) {
                    if (move == null) continue;
                    highLight(icons[move.getX()][move.getY()], move, true);
                }
                highLight(i,l, false);
            }
            
        }

        else if (!buttonStack.isEmpty()) {
            JButton fromB = buttonStack.pop(), toB=null;
            Location fromL = locationStack.pop(), toL=null;

            if (j.getPiece(l) != null && !buttonStack.contains(i)) {
                unHighLight(fromB, fromL, true);
                while(!buttonStack.empty()) {
                    unHighLight(buttonStack.pop(), locationStack.pop(), false);
                }
                doAction(icons[l.getX()][l.getY()], l);
            }

            else {
                while(!buttonStack.empty()) {
                    toB = buttonStack.pop();
                    toL = locationStack.pop();
                    if (toL.equals(l)) {
                        j.runCommand(String.format("move %s %s",fromL.toString(), toL.toString()));
                        isRun = true;
                        unHighLight(fromB, fromL, false);
                    }
                    unHighLight(toB, toL, false);
                }
                if (isRun) nextTrun();
            }
        }
    }

    private void nextTrun() {
        enableTurnPiece();
        j.checkWinner();
        if (!j.onGame) {
            if (j.getCurrentTurn()) {
                showMessage("Blue Team Wins!!");
            }
            else showMessage("Red Team Wins!!");
            System.exit(1);
        }
    }

    private void highLight(JButton i, Location l, boolean isEnable) {
        buttonStack.push(i);
        locationStack.push(l);
        i.setIcon(new ImageIcon(images.getImageHighlight(j.getPiece(l),l)));
        if (isEnable) enable(i);
        else disable(i);
    }

    private void unHighLight(JButton i, Location l, boolean isEnable) {
        i.setIcon(new ImageIcon(images.getImage(j.getPiece(l), l)));
        if (isEnable) enable(i);
        else disable(i);
    }

    private void disable(JButton i) {
        i.setDisabledIcon(i.getIcon());
        i.setEnabled(false);
    }

    private void enable(JButton i) {
        i.setEnabled(true);
    }


    // message handle **************
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);

    }

    // canvas handle ************************************
    @SuppressWarnings("serial")
    public class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(images.backgroundImage, 0, 0, this);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(images.backgroundImage.getWidth(this),
                    images.backgroundImage.getHeight(this));
        }
    }
}
