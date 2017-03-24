package main;

public class WinModel {
	@SuppressWarnings("unused")
	private String name;
	private String time;
	private String puzzleId;

	public void setName(String newName) {
		this.name = newName;
	}

	public void setTime(String newTime){
		this.time = newTime;
	}

	public void setPuzzleId(String newPuzzleId){
		this.puzzleId = newPuzzleId;
	}

	public String getTime() {
		return time;
	}

	public String getId() {
		return puzzleId;
	}
}
