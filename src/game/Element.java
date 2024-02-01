package game;


import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import networking.MPGameStage;
import soloGame.GameOverStage;
import soloGame.GameStage;
public class Element {
	private String type;
	protected Image img;
	protected ImageView imgView;
	protected GameStage gameStage;
	protected GameOverStage gameOverStage;
	protected int row, col;
	private MPGameStage MPgameStage;
	public final static Image letterA = new Image ("images/A.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterB = new Image ("images/B.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterC = new Image ("images/C.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterD = new Image ("images/D.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterE = new Image ("images/E.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterF = new Image ("images/F.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterG = new Image ("images/G.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterH = new Image ("images/H.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterI = new Image ("images/I.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterJ = new Image ("images/J.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterK = new Image ("images/K.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterL = new Image ("images/L.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterM = new Image ("images/M.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterN = new Image ("images/N.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterO = new Image ("images/O.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterP = new Image ("images/P.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterQ = new Image ("images/Q.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterR = new Image ("images/R.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterS = new Image ("images/S.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterT = new Image ("images/T.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterU = new Image ("images/U.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterV = new Image ("images/V.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterW = new Image ("images/W.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterX = new Image ("images/X.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterY = new Image ("images/Y.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image letterZ = new Image ("images/Z.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image exit = new Image ("images/exit.png",255,255,false,false);

	public final static int IMAGE_SIZE = 70;

	public final static String LAND_TYPE = "land";
	public final static String A_TYPE = "A";
	public final static String B_TYPE = "B";
	public final static String C_TYPE = "C";
	public final static String D_TYPE = "D";
	public final static String E_TYPE = "E";
	public final static String F_TYPE = "F";
	public final static String G_TYPE = "G";
	public final static String H_TYPE = "H";
	public final static String I_TYPE = "I";
	public final static String J_TYPE = "J";
	public final static String K_TYPE = "K";
	public final static String L_TYPE = "L";
	public final static String M_TYPE = "M";
	public final static String N_TYPE = "N";
	public final static String O_TYPE = "O";
	public final static String P_TYPE = "P";
	public final static String Q_TYPE = "Q";
	public final static String R_TYPE = "R";
	public final static String S_TYPE = "S";
	public final static String T_TYPE = "T";
	public final static String U_TYPE = "U";
	public final static String V_TYPE = "V";
	public final static String W_TYPE = "W";
	public final static String X_TYPE = "X";
	public final static String Y_TYPE = "Y";
	public final static String Z_TYPE = "Z";
	public final static String EXIT_TYPE = "Exit";

	public Element(String type,  GameStage gameStage) {
		this.type = type;
		this.gameStage = gameStage;

		switch(this.type) {
			case Element.A_TYPE: this.img = Element.letterA; break;
			case Element.B_TYPE: this.img = Element.letterB; break;
			case Element.C_TYPE: this.img = Element.letterC; break;
			case Element.D_TYPE: this.img = Element.letterD; break;
			case Element.E_TYPE: this.img = Element.letterE; break;
			case Element.F_TYPE: this.img = Element.letterF; break;
			case Element.G_TYPE: this.img = Element.letterG; break;
			case Element.H_TYPE: this.img = Element.letterH; break;
			case Element.I_TYPE: this.img = Element.letterI; break;
			case Element.J_TYPE: this.img = Element.letterJ; break;
			case Element.K_TYPE: this.img = Element.letterK; break;
			case Element.L_TYPE: this.img = Element.letterL; break;
			case Element.M_TYPE: this.img = Element.letterM; break;
			case Element.N_TYPE: this.img = Element.letterN; break;
			case Element.O_TYPE: this.img = Element.letterO; break;
			case Element.P_TYPE: this.img = Element.letterP; break;
			case Element.Q_TYPE: this.img = Element.letterQ; break;
			case Element.R_TYPE: this.img = Element.letterR; break;
			case Element.S_TYPE: this.img = Element.letterS; break;
			case Element.T_TYPE: this.img = Element.letterT; break;
			case Element.U_TYPE: this.img = Element.letterU; break;
			case Element.V_TYPE: this.img = Element.letterV; break;
			case Element.W_TYPE: this.img = Element.letterW; break;
			case Element.X_TYPE: this.img = Element.letterX; break;
			case Element.Y_TYPE: this.img = Element.letterY; break;
			case Element.Z_TYPE: this.img = Element.letterZ; break;
			case Element.EXIT_TYPE: this.img = Element.exit; break;
		}
		// call the functions that sets the image and handles the events
		this.setImageView();
		this.setMouseHandler();
	}
	public Element(String type,  MPGameStage mpgameStage) {
		this.type = type;
		this.MPgameStage = mpgameStage;

		switch(this.type) {
			case Element.A_TYPE: this.img = Element.letterA; break;
			case Element.B_TYPE: this.img = Element.letterB; break;
			case Element.C_TYPE: this.img = Element.letterC; break;
			case Element.D_TYPE: this.img = Element.letterD; break;
			case Element.E_TYPE: this.img = Element.letterE; break;
			case Element.F_TYPE: this.img = Element.letterF; break;
			case Element.G_TYPE: this.img = Element.letterG; break;
			case Element.H_TYPE: this.img = Element.letterH; break;
			case Element.I_TYPE: this.img = Element.letterI; break;
			case Element.J_TYPE: this.img = Element.letterJ; break;
			case Element.K_TYPE: this.img = Element.letterK; break;
			case Element.L_TYPE: this.img = Element.letterL; break;
			case Element.M_TYPE: this.img = Element.letterM; break;
			case Element.N_TYPE: this.img = Element.letterN; break;
			case Element.O_TYPE: this.img = Element.letterO; break;
			case Element.P_TYPE: this.img = Element.letterP; break;
			case Element.Q_TYPE: this.img = Element.letterQ; break;
			case Element.R_TYPE: this.img = Element.letterR; break;
			case Element.S_TYPE: this.img = Element.letterS; break;
			case Element.T_TYPE: this.img = Element.letterT; break;
			case Element.U_TYPE: this.img = Element.letterU; break;
			case Element.V_TYPE: this.img = Element.letterV; break;
			case Element.W_TYPE: this.img = Element.letterW; break;
			case Element.X_TYPE: this.img = Element.letterX; break;
			case Element.Y_TYPE: this.img = Element.letterY; break;
			case Element.Z_TYPE: this.img = Element.letterZ; break;
			case Element.EXIT_TYPE: this.img = Element.exit; break;
		}
		// call the functions that sets the image and handles the events
		this.setImageView();
		this.setMouseHandler();
	}

	public Element(String type, GameOverStage gameOverStage) {
		this.type = type;
		this.gameOverStage = gameOverStage;

		// load the images depending on the type
		this.img = Element.exit;
		// call the functions that sets the image and handles the events
		this.setImageView();
		this.setMouseHandler();
	}
	
	String getType(){
		return this.type;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return this.col;
	}


	public ImageView getImageView(){
		return this.imgView;
	}

	protected Image getImage(){
		return this.img;
	}

	// implement the functions that initializes the image view of this element and the event handlers you need
	void initRowCol(int i, int j) {
		this.row = i;
		this.col = j;
	}

	private void setMouseHandler(){
		Element element = this;
		this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
                switch(element.getType()) {
	    			case Element.EXIT_TYPE:
	    				GameOverStage.getScene().setCursor(Cursor.HAND);
	    				System.exit(0);
    	            	break;
                }
			}	//end of handle()
		});
	}


	private void setImageView() {
		// initialize the image view of this element
		this.imgView = new ImageView();
		this.imgView.setImage(this.img);
		this.imgView.setLayoutX(0);
		this.imgView.setLayoutY(0);
		this.imgView.setPreserveRatio(true);

		if(this.type.equals(Element.EXIT_TYPE)) {
			this.imgView.setFitWidth(255);
			this.imgView.setFitHeight(255);
		}else {
			this.imgView.setFitWidth(GameStage.CELL_WIDTH);
			this.imgView.setFitHeight(GameStage.CELL_HEIGHT);
		}

	}

	void setType(String type){
		this.type = type;
	}

}
