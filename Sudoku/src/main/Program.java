package main;

public class Program {
	public static void main (String[] args){
		PuzzleValidator puzzleValidator = new PuzzleValidator();
		SudokuValidator validator = new SudokuValidator();
		
		StatsModel statsModel = new StatsModel();
		WinModel winModel = new WinModel();
		SaveModel saveModel = new SaveModel();
		LoadModel loadModel = new LoadModel();
		GameModel gameModel = new GameModel(validator);
		CreateGameModel createGameModel = new CreateGameModel(puzzleValidator);
		
		Loader loader = new Loader(gameModel, puzzleValidator);
		
		LoadView loadView = new LoadView();		
		StatsView statsView = new StatsView(statsModel);
		WinView winView = new WinView(winModel);		
		SaveView saveView = new SaveView(saveModel);
		GameView gameView = new GameView(gameModel);
		CreateGameView createGameView = new CreateGameView(createGameModel);
		LaunchView launchView = new LaunchView();
		
		StatsController statsController = new StatsController(statsView, statsModel);			
		WinController winController = new WinController(winModel, winView, statsModel);
		LoadController loadController = new LoadController(loadView, loadModel);
		SaveController saveController = new SaveController(saveView, saveModel);
		LaunchController launchController = new LaunchController(launchView);
		GameController gameController = new GameController(gameModel, gameView, winModel, 
				loadModel, saveModel, loader);
		CreateGameController createGameController = new CreateGameController(createGameView, 
				createGameModel, gameModel, saveModel);
				
		
		saveView.addControl(saveController);
		winView.addControl(winController);
		gameView.addControl(gameController, saveController);
		createGameView.addControl(createGameController, gameController, saveController);
		loadView.addControl(loadController);
		statsView.addControl(statsController, launchController);
		launchView.addControllers(launchController, gameController, statsController, 
				createGameController, loadController);			
		
		gameController.addObserver(winController);
		loadController.addObserver(gameController);
		winController.addObserver(statsController);
		
		launchController.toggleScreen();
	}
}
