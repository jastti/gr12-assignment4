package tv.show;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import tv.show.Episode;
import tv.show.Episode.SortByEpisodeNum;
public class TvShow implements Comparable<TvShow>{
	public enum SortType{EpisodeNum,Title,Time}
	private String title;
	private String genre;
	private int numOfSeasons;
	private static ArrayList<TvShow> numOfTVShows;
	private Time totalLengthOfShow;
	private ArrayList<Episode> episodeList = new ArrayList<Episode>();
	private int selectedSeason = 0;

	private ArrayList<Episode> selectedSeasonEpisodeList = new ArrayList<Episode>();
	public TvShow(String title,String genre,String numOfSeasons){
		this.setTitle(title);
		this.setGenre(genre);
		this.setNumOfSeasons(Integer.parseInt(numOfSeasons));
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return the numOfSeasons
	 */
	public int getNumOfSeasons() {
		return numOfSeasons;
	}
	/**
	 * @param numOfSeasons the numOfSeasons to set
	 */
	public void setNumOfSeasons(int numOfSeasons) {
		this.numOfSeasons = numOfSeasons;
	}
	/**
	 * @return the numOfTVShows
	 */
	public static ArrayList<TvShow> getNumOfTVShows() {
		return numOfTVShows;
	}
	/**
	 * @param numOfTVShows the numOfTVShows to set
	 */
	public static void setNumOfTVShows(ArrayList<TvShow> numOfTVShows) {
		TvShow.numOfTVShows = numOfTVShows;
	}
	/**
	 * @return the totalLengthOfShow
	 */
	public Time getTotalLengthOfShow() {
		return totalLengthOfShow;
	}
	/**
	 * @param totalLengthOfShow the totalLengthOfShow to set
	 */
	public void setTotalLengthOfShow(Time totalLengthOfShow) {
		this.totalLengthOfShow = totalLengthOfShow;
	}
	/**
	 * @return the episodeList
	 */
	public ArrayList<Episode> getEpisodeList() {
		return episodeList;
	}
	/**
	 * @param episodeList the episodeList to set
	 */
	public void setEpisodeList(ArrayList<Episode> episodeList) {
		this.episodeList = episodeList;
	}
	
	public int searchName(String title, String episodesList) {
		return 0;
	}

	// Display how much of the show has been watched by outputting the following:
	// - Season # = # eps watched “out of” total # eps (list each season)
	// - # seasons completely watched
	// - # episodes unwatched
	public void showStatus() {
		if (!episodeList.isEmpty()) {
			
			List<Episode> watchedEpisode = new ArrayList<>();
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.isWatched()) {
					watchedEpisode.add(episode);
				}
			}
			System.out.println("Season # " + numOfSeasons + " \t" + watchedEpisode.size() + " watched “out of” total "
					+ episodeList.size());
			int unwated = episodeList.size() - watchedEpisode.size();
			System.out.println("Number of Episodes Unwatched: " + unwated);
			
		}else {
			System.out.println("There are no Episodes for this Tv show");
		}
	}
	// Display title, genre, # of seasons, # of episodes, and the total time of all
	// the episodes.
	public void displayTVShowDetails() {
		System.out.println();
		System.out.print("Show Title: "+ title +" ");
		System.out.print("Show Genre: "+ genre +" ");
		System.out.print("Num of Seasons: "+ numOfSeasons +" ");
		System.out.print("Num of Episodes: "+ episodeList.size() +" ");
		int hours=0, minutes=0,seconds = 0;
		for (Episode episode : episodeList) {
			
			hours += episode.getLengthOfEpisode().getNumOfHours();
			minutes += episode.getLengthOfEpisode().getNumOfMinutes();
			seconds += episode.getLengthOfEpisode().getNumOfSeconds();
		}
		int minutesToAdd = 0;
		while(seconds - 60 > 0) {
			minutesToAdd++;
			seconds = seconds - 60;
		}
		int hoursToAdd = 0;
		minutes = minutes + minutesToAdd;
		while(minutes - 60 > 0) {
			hoursToAdd++;
			minutes = minutes - 60;
		}
		hours = hours + hoursToAdd;
		Time time = new Time(hours,minutes,seconds);
		System.out.print("Total Time: "+ time.toString() +" ");
	}
	
	public void displayEpisodeTitles() {
		List <Episode> episodes = new ArrayList<Episode>();
		if (!episodeList.isEmpty()) {
			for (Episode episode : episodeList) {
				if (episode.getSeasonNum() == selectedSeason) {
					System.out.println("Episode Num: "+ episode.getEpisodeNum() + " Episode Title: " + episode.getEpisodeTitle() + " Episode Time: " + episode.getLengthOfEpisode().toString());
					episodes.add(episode);
					
				}
			}
			if (selectedSeasonEpisodeList.isEmpty()) {
				selectedSeasonEpisodeList.addAll(episodes);
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
		
	}
	
	public void sortEpisodes(TvShow.SortType sortType) {
		switch (sortType) {
		case EpisodeNum:
			loadSelectedSeasonEpisodeList();
			Collections.sort(episodeList, new Episode.SortByEpisodeNum());
			displayEpisodeTitles();
			break;

		case Time:
			loadSelectedSeasonEpisodeList();
			Collections.sort(episodeList, new Episode.SortByEpisodeTime());
			displayEpisodeTitles();
			break;
		case Title:
			loadSelectedSeasonEpisodeList();
			Collections.sort(episodeList, new Episode.SortByEpisodeTitle());
			displayEpisodeTitles();
			break;
		default:
			break;
		}
	}
	private void loadSelectedSeasonEpisodeList() {
		if (selectedSeasonEpisodeList.isEmpty()) {
			List <Episode> episodes = new ArrayList<Episode>();
			for (Episode episode : episodeList) {
				if (episode.getSeasonNum() == selectedSeason) {
					episodes.add(episode);
					
				}
			}
			selectedSeasonEpisodeList.addAll(episodes);
		}
	}
	/**
	 * Prints title of the TV Show
	 */
	public void printTitle() {
		System.out.println("Show Title:" + this.getTitle());
	}
	public void displayEpisodeDetails(int input) {
		if (!episodeList.isEmpty()) {
			boolean printed = false;
			loadSelectedSeasonEpisodeList();
			//option 1: Call Collection binary search
			if (0 < input && input <= selectedSeasonEpisodeList.size()) // if input the episode number is valid, then sort and search; 
			{
				input=input-1; //index decrease 1, in case the ArrayList overflow.
				Collections.sort(selectedSeasonEpisodeList, new Episode.SortByEpisodeNum());
				if(Collections.binarySearch(selectedSeasonEpisodeList, selectedSeasonEpisodeList.get(input), new Episode.SortByEpisodeNum()) != -1)
				{
					printed = true;
					System.out.println("Season Num: "+ selectedSeasonEpisodeList.get(input).getSeasonNum() +" Episode Num: "+ selectedSeasonEpisodeList.get(input).getEpisodeNum() + " Episode Title: " + selectedSeasonEpisodeList.get(input).getEpisodeTitle() + " Episode Watched: " + selectedSeasonEpisodeList.get(input).isWatched() + " Episode Time: " + selectedSeasonEpisodeList.get(input).getLengthOfEpisode().toString());
				}
			 	
				else {
				printed = false;
				}
			}
			if (!printed) {
				System.out.println("Episode Number does not exist");
	        }
		
			//option 2: Define binary search
//			int left, right, pivot;
//			left = pivot = 0;
//			right = selectedSeasonEpisodeList.size();
//			if (0 < input && input <= right) {
//				Collections.sort(selectedSeasonEpisodeList, new Episode.SortByEpisodeNum());
//				while (left <= right) {
//					pivot = left + (right - left) / 2;
//					if (selectedSeasonEpisodeList.get(pivot).getEpisodeNum() == input ) {
//							System.out.println("Season Num: "+ selectedSeasonEpisodeList.get(pivot).getSeasonNum() +" Episode Num: "+ selectedSeasonEpisodeList.get(pivot).getEpisodeNum() + " Episode Title: " + selectedSeasonEpisodeList.get(pivot).getEpisodeTitle() + " Episode Watched: " + selectedSeasonEpisodeList.get(pivot).isWatched() + " Episode Time: " + selectedSeasonEpisodeList.get(pivot).getLengthOfEpisode().toString());
//							printed = true;
//							break;
//					}
//					if (selectedSeasonEpisodeList.get(pivot).getEpisodeNum() > input ) {
//						right = pivot - 1;
//						printed = false;
//					} 
//					else if((selectedSeasonEpisodeList.get(pivot).getEpisodeNum() < input  )){
//						left = pivot + 1;
//						printed = false;
//					}
//				
//				}
//			}
//			else {
//					printed = false;
//			}
//			if (!printed) {
//				System.out.println("Episode Number does not exist");
//			}
// the end line of option 2			
			
//			option 3: Define binary search
//				for (Episode episode : episodeList) {
//					if (episode.getEpisodeNum() == input && episode.getSeasonNum() == selectedSeason) {
//						System.out.println("Season Num: "+ episode.getSeasonNum() +" Episode Num: "+ episode.getEpisodeNum() + " Episode Title: " + episode.getEpisodeTitle() + " Episode Watched: " + episode.isWatched() + " Episode Time: " + episode.getLengthOfEpisode().toString());
//						printed = true;
//					}
//					
//				}
//				
//			if (!printed) {
//						System.out.println("Episode Number does not exist");
//			}
// the end line of option 3		
		
		}
		else { //if the Episode is empty.
			System.out.println("There are no Episodes for this season");
		}
			
	
}
	public void removeEpisode(int episodeNum) {
		if (!episodeList.isEmpty()) {
			Episode episodeToRemove = null;
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.getEpisodeNum() == episodeNum && episode.getSeasonNum() == selectedSeason) {
					episodeToRemove = episode;
					exist = true;
					break;
				}
				
			}
			if (!exist) {
				System.out.println("Episode Number does not exist");
			}else {
				episodeList.remove(episodeToRemove);
				selectedSeasonEpisodeList.remove(episodeToRemove);
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
		
	}
	public void removeEpisode(String inputLine) {
		if (!episodeList.isEmpty()) {
			Episode episodeToRemove = null;
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.getEpisodeTitle().equals(inputLine)  && episode.getSeasonNum() == selectedSeason) {
					episodeToRemove = episode;
					exist = true;
					break;
				}
				
			}
			if (!exist) {
				System.out.println("Episode Number does not exist");
			}else {
				episodeList.remove(episodeToRemove);
				selectedSeasonEpisodeList.remove(episodeToRemove);
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
		
	}
	public void removeEpisode(int startRange, int endRange) {
		if (!episodeList.isEmpty()) {
			
			List<Episode> episodeToRemove = new ArrayList<>();
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.getEpisodeNum() >= startRange && episode.getEpisodeNum() <= endRange  && episode.getSeasonNum() == selectedSeason) {
					episodeToRemove.add(episode);
					exist = true;
				}
				
			}
			if (!exist) {
				System.out.println("Episode Number does not exist");
			}else {
				episodeList.removeAll(episodeToRemove);
				selectedSeasonEpisodeList.removeAll(episodeToRemove);
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
	}
	public void removeEpisode(boolean b) {
		if (!episodeList.isEmpty()) {
			
			List<Episode> episodeToRemove = new ArrayList<>();
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.isWatched() == b  && episode.getSeasonNum() == selectedSeason) {
					episodeToRemove.add(episode);
					exist = true;
				}
				
			}
			if (!exist) {
				System.out.println("There are not watched Episodes");
			}else {
				episodeList.removeAll(episodeToRemove);
				selectedSeasonEpisodeList.removeAll(episodeToRemove);
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
		
	}
	public int getSelectedSeason() {
		return selectedSeason;
	}
	public void setSelectedSeason(int selectedSeason) {
		this.selectedSeason = selectedSeason;
	}
	public void setEpisodeWatched(int input) {
		if (!episodeList.isEmpty()) {
			boolean printed = false;
			for (Episode episode : episodeList) {
				if (episode.getEpisodeNum() == input  && episode.getSeasonNum() == selectedSeason) {
					episode.setEpisodeWatched(true);
					System.out.println("Season Num: "+ episode.getSeasonNum() +" Episode Num: "+ episode.getEpisodeNum() + " Episode Title: " + episode.getEpisodeTitle() + " Episode Watched: " + episode.isWatched() + " Episode Time: " + episode.getLengthOfEpisode().toString());
					printed = true;
				}
				
			}
			if (!printed) {
				System.out.println("Episode Number does not exist");
			}
		}else {
			System.out.println("There are no Episodes for this season");
		}
		
	}
	//String episodeTitle, int seasonNum, int episodeNum, String lengthOfEpisode
	public void addEpisode(String episodeNumber, String title2, String episodeTime) {
		Episode episode = new Episode(title2, selectedSeason, Integer.parseInt(episodeNumber), episodeTime);
		boolean duplicate = false;
		for (Episode episode1 : episodeList) {
			if (episode1.getEpisodeNum() == episode.getEpisodeNum() && episode.getSeasonNum() == selectedSeason) {
				duplicate = true;
			}else if (episode1.compareTo(episode) == 0) {
				duplicate = true;
			}
		}
		if (!duplicate) {

			episodeList.add(episode);
			selectedSeasonEpisodeList.add(episode);// 同步已选searson下的episode
		}else {
			System.out.println("Duplicate Episode Details in the season");
		}
	}
	public void removeEpisode(int seasonNumber, boolean removeAll) {
		if (!episodeList.isEmpty()) {
			
			List<Episode> episodeToRemove = new ArrayList<>();
			boolean exist = false;
			for (Episode episode : episodeList) {
				if (episode.getSeasonNum() == seasonNumber) {
					episodeToRemove.add(episode);
					exist = true;
				}
			}
				
			
			if (!exist) {
				System.out.println("There are not Episodes with given season number in this show");
			}else {
				episodeList.removeAll(episodeToRemove);
			}
		}else {
			System.out.println("There are no Episodes for this Tv show");
		}
		
	}
	
	@Override
	public int compareTo(TvShow o) {
//		System.out.println("Comparing");
		if (this.title.equals(o.getTitle())) {
			for (Episode episode : episodeList) {
				for (Episode episode1 : o.episodeList) {
					if (episode1.compareTo(episode)==0) {
						return 0;
					}
				}
			}
		}
		return 1;
	}
}
