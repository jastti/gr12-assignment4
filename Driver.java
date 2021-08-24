package tv.show;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Main Program Class Driver
 *
 */
public class Driver {
	private static ArrayList<TvShow> lstTvShows = new ArrayList<TvShow>();
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.startProgram();
	}

	private static final String command = ">";
	private static final String msgInvalidInput = "Invalid Input, please enter valid value";
	private static TvShow selectedTvShow = null;

	public void startProgram() {
		Scanner scanner = new Scanner(System.in);

		printMainMenu();
		boolean breakMainMenuLoop = false;
		while (!breakMainMenuLoop) {
			int input = 0;
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {

				System.out.print(command);
				continue;
			} else {
				try {
					input = Integer.parseInt(inputLine);
				} catch (NumberFormatException ex) {
					System.out.println(msgInvalidInput);
					System.out.print(command);
					continue;
				}
			}
			switch (input) {
			case 1: {
				optionOne(scanner);
				break;
			}
			case 2: {
				optionTwo(scanner);
				break;
			}
			case 3: {
				breakMainMenuLoop = true;
				break;
			}
			default: {
				System.out.println(msgInvalidInput);
				System.out.print(command);
				continue;
			}
			}
			if (!breakMainMenuLoop) {
				printMainMenu();
			}
		}
		System.out.println("Application closing!");
		scanner.close();
	}

	public void printMainMenu() {

		System.out.println("1) Accessing your TV Shows list");
		System.out.println("2) Accessing within a particular TV show");
		System.out.println("3) Exit");
		System.out.print(command);
	}

	public void optionOne(Scanner scanner) {
		printOptionOneMenu();
		boolean breakOptionOneLoop = false;
		while (!breakOptionOneLoop) {
			int input = 0;
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {

				System.out.print(command);
				continue;
			} else {
				try {
					input = Integer.parseInt(inputLine);
				} catch (NumberFormatException ex) {
					System.out.println(msgInvalidInput);
					System.out.print(command);
					continue;
				}
			}
			switch (input) {
			case 1: {
				// Display a list of the TV show titles
				displayTVShowList();
				break;
			}
			case 2: {
				// Display title, genre, # of seasons, # of episodes, and the total time of all
				// the episodes.

				System.out.print("> Enter Show Title: ");
				inputLine = scanner.nextLine();
				TvShow tvShow = selectShow(inputLine);
				if (tvShow == null) {
					System.out.println("Season or Show Title does not Exist");
					return;
				}else {
					tvShow.displayTVShowDetails();
				}
				break;
			}

			case 3: {
				// Prompt user to type in the file name to read. Assume the user has not watched
				// any of them.
				System.out.println("> Please Enter File Name(it must exist in project folder)");
				String fileName = scanner.nextLine();
				addShow(fileName);
				break;
			}

			case 4: {
				// Give choice to remove by show title or season # (all episodes in the season
				// will be deleted.)
				System.out.println("> Please Enter Show Title To Perform remove:");
				String showTitle = scanner.nextLine();

				System.out.println("> Please Enter 1 to Remove Whole Show");
				System.out.println("> Please Enter 2 to Remove a specific season");
				int option = 0;
				while(true) {
					try {
					option = scanner.nextInt();
					break;
					}catch(Exception ex) {
						System.out.println("Wrong Input");
					}
				}
				switch (option) {
				case 1:
					TvShow tvShowToRemove = null;
					for (TvShow tvShow : lstTvShows) {
						if (tvShow.getTitle().equals(showTitle)) {
							tvShowToRemove = tvShow;
							break;
						}
					}
					if (tvShowToRemove != null) {
						lstTvShows.remove(tvShowToRemove);
					}else {
						System.out.println("\nNo Tv show with this title found for delition");
					}
					break;
				case 2:
					System.out.println("> Please Enter Season Number To Remove");
					int seasonNumber = scanner.nextInt();
					TvShow tvShowToRemoveSeason = selectShow(showTitle);
					if (tvShowToRemoveSeason != null) {
						tvShowToRemoveSeason.removeEpisode(seasonNumber, true);
						
					}else {
						System.out.println("Tv show with this season does not Exist");
					}
					break;

				default:
					break;
				}
				
				break;
			}

			case 5: {
				// Display how much of the show has been watched by outputting the following:
				// - Season # = # eps watched â€œout ofâ€� total # eps (list each season)
				// - # seasons completely watched
				// - # episodes unwatched
				System.out.println("> Please Enter Show Title: ");
				String showTitle = scanner.nextLine();

				TvShow tvShowToRemoveSeason = selectShow(showTitle);
				if (tvShowToRemoveSeason != null) {
					tvShowToRemoveSeason.showStatus();
				}else {
					System.out.println("\nThere is No tv show with this title\n");
				}
				break;
			}

			case 6: {
				breakOptionOneLoop = true;
				break;
			}
			default: {
				System.out.println(msgInvalidInput);
				System.out.print(command);
				continue;
			}
			}
			if (!breakOptionOneLoop) {
				printOptionOneMenu();
			}
		}
	}

	public void printOptionOneMenu() {
		
		System.out.println("\n1) Display a list of all your TV shows");
		System.out.println("2) Display information on a particular TV show");
		System.out.println("3) Add a TV show");
		System.out.println("4) Remove (2 options)");
		System.out.println("5) Show status");
		System.out.println("6) Return back to main menu");
		System.out.print(command);
	}
	
	public void optionTwo(Scanner scanner) {
		boolean isSelected = false;
		String[] queriesAnswers = preOptionTwoQueries(scanner);
		isSelected = selectShow(queriesAnswers);
		if (!isSelected) {
			System.out.println("Season or Show Title does not Exist");
			return;
		}else {
			selectedTvShow.setSelectedSeason(Integer.parseInt(queriesAnswers[1]));
		}
		
		printOptionTwoMenu();
		boolean breakOptionTwoLoop = false;
		while (!breakOptionTwoLoop) {
			int input = 0;
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {

				System.out.print(command);
				continue;
			} else {
				try {
					input = Integer.parseInt(inputLine);
				} catch (NumberFormatException ex) {
					System.out.println(msgInvalidInput);
					System.out.print(command);
					continue;
				}
			}
			switch (input) {
			case 1: {
				// Display a list of the episode titles
//				selectedTvShow.sortEpisodes(TvShow.SortType.EpisodeNum);
				selectedTvShow.displayEpisodeTitles();
				break;
			}
			case 2: {
				// Display episode title, season #, episode #, whether it has been watched, and
				// time (hh:mm:ss).
				System.out.print("> Enter Episode Number:");
				inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals("")) {

					System.out.print(command);
					continue;
				} else {
					try {
						input = Integer.parseInt(inputLine);
						
//						selectedTvShow.sortEpisodes(TvShow.SortType.EpisodeNum);
						selectedTvShow.displayEpisodeDetails(input);
					} catch (NumberFormatException ex) {
						System.out.println(msgInvalidInput);
						System.out.print(command);
						continue;
					}
				}
				break;
			}

			case 3: {
				// Set the episode to be watched
				System.out.print("> Enter Episode Number:");
				inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals("")) {

					System.out.print(command);
					continue;
				} else {
					try {
						input = Integer.parseInt(inputLine);
						selectedTvShow.setEpisodeWatched(input);
					} catch (NumberFormatException ex) {
						System.out.println(msgInvalidInput);
						System.out.print(command);
						continue;
					}
				}
				break;
			}

			case 4: {
				// Prompt user for episode title, episode #, and time (hh:mm:ss).
				// - It must be added to the current season.
				// - The episode should be set to unwatched.
				System.out.println("Please Enter Episode Title");
				System.out.print(command);
				String title = scanner.nextLine();
				System.out.println("Please Enter Episode Number");
				System.out.print(command);
				String episodeNumber = scanner.nextLine();
				String episodeTime = "";
				System.out.println("Please Enter Episode Time(HH:MM:SS)");
				String regex = "^(((([0-1][0-9])|(2[0-3])):?[0-5][0-9]:?[0-5][0-9]+$))";
				while (!episodeTime.matches(regex)) {
					System.out.print(command);
					episodeTime = scanner.nextLine();
					if (!episodeTime.matches(regex)) {
						System.out.println("Invalid Time Format");
					}
				}
				selectedTvShow.addEpisode(episodeNumber,title,episodeTime);
				
				break;
			}

			case 5: {
				// Give choice to remove episode by episode #, title, a range of episodes (ie.
				// episodes #3 to #9), or
				// remove only the watched episodes.
				optionTwoFiveDeleteEpisode(scanner);
				break;
			}

			case 6: {
				// Give choice to sort the episodes by episode number, title, or time
				// (re-display list after).
				
				System.out.println("\n1) Sort episode by episode #");
				System.out.println("2) Sort episode by episode Title");
				System.out.println("3) Sort episode by Time");
				System.out.println("4) Return back to menu");
				System.out.print(command);
				inputLine = scanner.nextLine();
				boolean breakSortLoop = false;
				while(!breakSortLoop) {
					if (inputLine == null || inputLine.equals("")) {
	
						System.out.print(command);
						continue;
					} else {
						try {
							input = Integer.parseInt(inputLine);
						} catch (NumberFormatException ex) {
							System.out.println(msgInvalidInput);
							System.out.print(command);
							continue;
						}
						switch (input) {
						case 1:
							selectedTvShow.sortEpisodes(TvShow.SortType.EpisodeNum);
							breakSortLoop = true;
							break;
						case 2:
							selectedTvShow.sortEpisodes(TvShow.SortType.Title);
							breakSortLoop = true;
							break;
						case 3:
							selectedTvShow.sortEpisodes(TvShow.SortType.Time);
							breakSortLoop = true;
							break;
						case 4:
							breakSortLoop = true;
							break;

						default:
							break;
						}
					}
				}
				
				break;
			}

			case 7: {
				breakOptionTwoLoop = true;
				break;
			}
			default: {
				System.out.println(msgInvalidInput);
				System.out.print(command);
				continue;
			}
			}
			if (!breakOptionTwoLoop) {
				printOptionTwoMenu();
			}
		}
	}
	
	private String[] preOptionTwoQueries(Scanner scanner) {
		selectedTvShow = null;
		System.out.println("> Please Enter Show Title");
		System.out.print(command);
		String showTitle = scanner.nextLine();
		System.out.println("> Please Enter the Season#");
		System.out.print(command);
		String SeasonNumber = scanner.nextLine();
		String [] answers = {showTitle,SeasonNumber};
		return answers;
	}
	private boolean selectShow(String[] queriesAnswers) {
		
		try {
			String title = queriesAnswers[0];
			int SeasonNumber = Integer.parseInt(queriesAnswers[1]);
			for (TvShow element : lstTvShows) {
				if (element.getTitle().equals(title)) {
					selectedTvShow = element;
					return true;
				}
			}
			return false;
		}catch(Exception ex) {
			System.out.println("An Error occurred while Selecting the show. Error Message:" + ex.getMessage());
			return false;
		}
	}
	
	private TvShow selectShow(String showTitle) {
		
		try {
			for (TvShow element : lstTvShows) {
				if (element.getTitle().equals(showTitle)) {
					return element;
				}
			}
			return null;
		}catch(Exception ex) {
			System.out.println("An Error occurred while Selecting the show. Error Message:" + ex.getMessage());
			return null;
		}
	}

	public void printOptionTwoMenu() {
		System.out.println("\n1) Display all episodes (in the last sorted order)");
		System.out.println("2) Display information on a particular episode");
		System.out.println("3) Watch an episode");
		System.out.println("4) Add an episode");
		System.out.println("5) Remove episode");
		System.out.println("6) Sort episodes");
		System.out.println("7) Return back to main menu");
		System.out.print(command);
	}
	
	public void optionTwoFiveDeleteEpisode(Scanner scanner) {

		printOptionTwoSub5MenuDeleteEpisode();
		boolean breakOptionTwoFiveLoop = false;
		while (!breakOptionTwoFiveLoop) {
			int input = 0;
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {

				System.out.print(command);
				continue;
			} else {
				try {
					input = Integer.parseInt(inputLine);
				} catch (NumberFormatException ex) {
					System.out.println(msgInvalidInput);
					System.out.print(command);
					continue;
				}
			}
			switch (input) {
			case 1: {
				// Remove episode by episode #
				int episodeNum = -1;
				System.out.println("Select Episode #: ");
				System.out.print(command);
				while(true) {

					inputLine = scanner.nextLine();
					try {
						episodeNum = Integer.parseInt(inputLine);
						break;
					}catch(Exception ex) {
						System.out.println(msgInvalidInput);
						System.out.print(command);
						continue;
					}
				}
				selectedTvShow.removeEpisode(episodeNum);
				break;
			}
			case 2: {
				// remove episode by episode Title
				System.out.println("Select Episode Title: ");
				System.out.print(command);
				inputLine = scanner.nextLine();
				selectedTvShow.removeEpisode(inputLine);
				break;
			}

			case 3: {
				// Remove episode by range
				int startRange = 0;
				int endRange = 0;
				while(true) {

					System.out.println("Select Episode # Start Range");
					System.out.print(command);
					inputLine = scanner.nextLine();
					try {
						startRange = Integer.parseInt(inputLine);
						if (startRange <= 0) {
							System.out.println("Start Range cannot be less than or equal to zero.");
							continue;
						}
						break;
					}catch(Exception ex) {
						System.out.println(msgInvalidInput);
						continue;
					}
				}
				
				while(true) {

					System.out.println("Select Episode # End Range");
					System.out.print(command);
					inputLine = scanner.nextLine();
					try {
						endRange = Integer.parseInt(inputLine);
						if (endRange <= 0 || endRange < startRange) {
							System.out.println("End Range cannot be less than or equal to zero or it cannot be less than start range.");
							continue;
						}
						break;
					}catch(Exception ex) {
						System.out.println(msgInvalidInput);
						continue;
					}
				}

				selectedTvShow.removeEpisode(startRange, endRange);
				
				break;
			}

			case 4: {
				// Remove watched episodes
				selectedTvShow.removeEpisode(true);
				break;
			}

			case 5: {
				breakOptionTwoFiveLoop = true;
				break;
			}
			default: {
				System.out.println(msgInvalidInput);
				System.out.print(command);
				continue;
			}
			}
			if (!breakOptionTwoFiveLoop) {
				printOptionTwoSub5MenuDeleteEpisode();
			}
		}
	}
	
	public void printOptionTwoSub5MenuDeleteEpisode() {
		// Give choice to remove episode by episode #, title, a range of episodes (ie.
		// episodes #3 to #9), or
		// remove only the watched episodes.
		System.out.println("\n1) Remove episode by episode #");
		System.out.println("2) remove episode by episode Title");
		System.out.println("3) Remove episode by range");
		System.out.println("4) Remove watched episodes");
		System.out.println("5) Return back to menu");
		System.out.print(command);
	}


	private void displayTVShowList() {
		if (lstTvShows != null && !lstTvShows.isEmpty()) {
			for (TvShow element : lstTvShows) {
				System.out.print(command);
				System.out.print("Id: " + lstTvShows.indexOf(element) + " ");
				element.printTitle();
			}
		}else {
			System.out.println("\nThere is No Tv Show Data to show\n");
		}
		
	}

	private void addShow(String inputFileName) {
		
		try {
			File myObj = new File(inputFileName);
			Scanner myReader = new Scanner(myObj);
			int lineIndex = 0;
			boolean isShowDataEnd = false;
			boolean episodesCompleted = true;
			String showTitle = "";
			String genre = "";
			String numOfSeasons = "";
			String numOfEpisodes = "";
			TvShow show = null;
			int numOfEpisodeIndex = 0;

			String episodeNumber = "";
			String episodeTitle = "";
			String episodeTime = "";
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
//				System.out.println(data);
				if (!isShowDataEnd) {
					if (lineIndex == 0) {
						showTitle = data;
					} else if (lineIndex == 1) {
						genre = data;
						isShowDataEnd = true;
						lineIndex = -1;
						show = new TvShow(showTitle,genre,"1");
					} 
//					else if (lineIndex == 2) {
//						numOfSeasons = data.split(" ")[1];
//					} else if (lineIndex == 3) {
//						numOfEpisodes = data;
//						isShowDataEnd = true;
//						lineIndex = -1;
//						show = new TvShow(showTitle,genre,"1");
//					}
				} else {
					if (episodesCompleted) {
						if (lineIndex == 0) {
							numOfSeasons = data.split(" ")[1];
							show.setNumOfSeasons(show.getNumOfSeasons()+1);
						}else if(lineIndex == 1){
							numOfEpisodes = data;
							episodesCompleted = false;
						}
					}
					
					else if (numOfEpisodeIndex < Integer.parseInt(numOfEpisodes)) {
						if (lineIndex == 2) {
//							System.out.println("lineIndex == 0:" + data);
							episodeNumber = data.split(" ")[1];
//							System.out.println(episodeNumber);
						}else if (lineIndex == 3) {
//							System.out.println("lineIndex == 1:" + data);
							episodeTitle = data;
						}else if (lineIndex == 4) {
//							System.out.println("lineIndex == 2:" + data);
							episodeTime = data;
							Episode episode = new Episode(episodeTitle,Integer.parseInt(numOfSeasons),Integer.parseInt(episodeNumber),episodeTime);
							if (!show.getEpisodeList().contains(episode)) {

								show.getEpisodeList().add(episode);
							}else {
								System.out.println("Episode already exist with season");
							}
							numOfEpisodeIndex++;
							if (numOfEpisodeIndex < Integer.parseInt(numOfEpisodes)) {
								lineIndex = 1;
								episodeNumber = "";
								episodeTitle = "";
								episodeTime = "";
							}else {
								System.out.println("Season Changed");
								episodesCompleted = true;
								lineIndex = -1;		
								numOfEpisodeIndex = 0;
							}
						}
					}
					
				}

				lineIndex++;
			}
			myReader.close();
			boolean a = false;
			for (TvShow tvShow : lstTvShows) {
				if (show.compareTo(tvShow)==0) {
					a = true;
					System.out.println("Show with same title already exist and season and episode number is also duplicate");
				}
			}
			if (!a) {
				show.setNumOfSeasons(show.getNumOfSeasons()-1);
				lstTvShows.add(show);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("An Error occurred while reading from file. Error Message:" + e.getMessage());
			return;
		}
	}

	private void addEpisode(String tvShow, String season, String epiSode) {

	}

	private void removeEpisode(String epiSodeTitle, String startEpisode, String endEpisode, String watched) {

	}
}
