package view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import manager.DefaultElementManager;
import manager.ElementManager;
import model.element.Element;
import model.element.ElementType;
import util.Util;

public class WorldFX extends Application implements Runnable, WorldView {

    private final static int eHeight = 40;
    private final static int eWidth = 40;

    private final List<Element> uninitializedElements = new ArrayList<>();
    private final List<Vector> uninitializedElementsPositions = new ArrayList<>();

    private Grid grid;
    private Legend legend;
    private ImageLoader loader;
    private ElementManager manager;
    private Canvas canvas;
    private final int tick = 1000;
    private final Thread t;

    public WorldFX() {
        this.t= new Thread(this);
    }

    @SuppressWarnings("unused")
    public void init() {

        String[] plan = {
                "################################################",
                "#  o   #    o      #                           #",
                "#                   ~~~          &             #",
                "#     ~    #####                          *    #",
                "##  *      #   #    ##       &                 #",
                "###       o   o#     #              o          #",
                "#  ~~~      ###   ~~~#             ~~     ~~   #",
                "#   ###o        ~~     @           o           #",
                "#   ##       o  ~~                     *  ~~   #",
                "# #  # ~~~     o       ##   &   ~~~            #",
                "#    #  o   oo      ~~~               ~~~      #",
                "#                            o o               #",
                "#    #             ~~    o           ~ *   *   #",
                "#    #     o       ~~                          #",
                "#    #      o      ~  ~    o     o     ~~      #",
                "#    #     o                  *      ~         #",
                "#    #     o         ~~~  o                    #",
                "#  o  #     o       ~~~             *          #",
                "# #  # ~~~     o       ##       ~~~            #",
                "# #  #         o       ##       ~~~            #",
                "# #  # ~~~     o       ##               &      #",
                "# #  #         *       ##       ~~~            #",
                "################################################"};

        String[] plan1 = {
                "############################",
                "#  o   #    o      #      ##",
                "#         $       ~~~      #",
                "#     ~~     #####         #",
                "##   * ~~     #   #    ##  #",
                "###   *    o   o#  ~~   #  #",
                "#           ###      #     #",
                "#   ###o *  ~~         *  @#",
                "#   ##     ~~  o      *    #",
                "# #  #       ~~  o       ###",
                "#    #   &                 #",
                "#    #      ~~~      &     #",
                "#  $ #         $           #",
                "#    #      ~~~         &  #",
                "#    #     oo ~~           #",
                "############################"
        };
        ElementManager manager = new DefaultElementManager();
        try {
            this.grid = new Grid(plan1[0].length(), plan1.length);
            this.legend = new SimpleLegend(manager);
            this.loader = new SimpleImageLoader();
            this.manager = manager;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        for(int i=0; i< plan1.length; i++) {
            char[] chars = plan1[i].toCharArray();
            for(int k=0; k< chars.length; k++) {
                grid.setGridContent(new Vector(k, i), Util.elementOf(chars[k], legend));
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple World");
        Group root = new Group();
        Canvas canvas = new Canvas(grid.getWidth() * eWidth, grid.getHeight() * eHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.canvas = canvas;
        drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.show();
        initWorld();

    }

    @Override
    public void run() {
        this.initializeElements();
        while(true) {
            try {
                Thread.sleep(getTick());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkUnitialized();
            for(int i=0; i< grid.getHeight(); i++) {
                for(int k=0; k< grid.getWidth(); k++) {
                    Vector pos = new Vector(k,i);
                    synchronized(Util.lockOn()) {
                        Element elem = grid.getGridContent(pos);
                        if(!elem.isEternal()) {
                            manager.decrementLife(elem);
                            if(!manager.isAlive(elem)) {
                                manager.remove(elem);
                                removeElement(elem);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getTick() {
        return tick;
    }

    public static void main(String ... args) throws Exception {
        Application.launch(args);
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }

    @Override
    public ElementManager getManager() {
        return manager;
    }

    @Override
    public void eat(Element element, Vector from, Direction to) {
        if(grid.getGridContent(from).equals(element)) {
            Vector newPos = from.plus(to.toVector());
            Element consumed = grid.getGridContent(newPos);
            if(element.consumes().contains(consumed.getElementType())) {
                Element eSpace = getManager().space();
                grid.setGridContent(newPos, element);
                getManager().augmentLifeBy(element,
                        getManager().getLife(consumed));
                getManager().remove(consumed);
                grid.setGridContent(from, eSpace);
                refreshPosition(newPos, element);
                refreshPosition(from, eSpace);
            }
        }
    }

    @Override
    public void move(Element element, Vector from, Direction to) {
        if(grid.getGridContent(from).equals(element)) {
            Vector newPos = from.plus(to.toVector());
            if(grid.getGridContent(newPos).getElementType() == ElementType.SPACE) {
                Element eSpace = getManager().space();
                grid.setGridContent(newPos, element);
                grid.setGridContent(from, eSpace);
                refreshPosition(newPos, element);
                refreshPosition(from, eSpace);
            }
        }
    }

    @Override
    public void reproduce(Element element, Vector from, Direction to) {
        if(grid.getGridContent(from).equals(element)) {
            Element baby = legend.get(element.getElementType());
            Vector newPos = from.plus(to.toVector());
            grid.setGridContent(newPos, baby);
            baby.init(this, newPos);
            refreshPosition(newPos, baby);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<grid.getHeight();i++) {
            for(int k=0; k< grid.getWidth(); k++) {
                sb.append(grid.getGridContent(new Vector(k, i)).getElementType().getElement());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void drawShapes(GraphicsContext gc) throws Exception {
        for(int i=0;i<grid.getHeight();i++) {
            for(int k=0; k< grid.getWidth(); k++) {
                gc.drawImage(loader.load(grid.getGridContent(new Vector(k, i)).getElementType()),
                        k * eWidth ,
                        i * eHeight ,
                        eWidth,
                        eHeight);
            }
        }
    }

    private void initWorld() {
        this.t.start();
    }

    private void initializeElements() {
        for(int i=0; i< grid.getHeight(); i++) {
            for(int k=0; k< grid.getWidth(); k++) {
                Vector pos = new Vector(k,i);
                Element elem = grid.getGridContent(pos);
                elem.init(this, pos);
            }
        }
    }

    private void checkUnitialized() {
        synchronized(Util.lockOn()) {
            int index = 0;
            for(Element e : uninitializedElements) {
                e.init(this, uninitializedElementsPositions.get(index));
                index++;
            }
            uninitializedElements.clear();
            uninitializedElementsPositions.clear();
        }
    }

    private void removeElement(Element e) {
        Vector position = e.getPosition();
        Element eSpace = manager.space();
        getGrid().setGridContent(position, eSpace);
        refreshPosition(position, eSpace);
    }

    private void refreshPosition(Vector pos, Element e) {
        int k = pos.getX();
        int i = pos.getY();
        /**
         * without delegating to the FX thread we will likely end up in a deadlock situation
         */
        Platform.runLater(new Runnable() {
            public void run() {
                canvas.getGraphicsContext2D().drawImage(loader.load(e.getElementType()),
                        k * eWidth ,
                        i * eHeight ,
                        eWidth,
                        eHeight);
            }
        });

    }
}