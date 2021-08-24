package tv.show;

import java.util.Comparator;

/**
 * An Episode has a title, season #, episode #, boolean to indicate whether it has been watched or
 * not, and a Time variable that contains the total length of the episode (in hh:mm:ss).
 */
public class Episode implements Comparable<Episode>{
	private String episodeTitle;
	private int seasonNum;
	private int episodeNum;
	private boolean watched;
	private Time lengthOfEpisode;
	/**
	 * Constructor to initialize Episode for a TV show
	 * @param episodeTitle episode title
	 * @param seasonNum season  number
	 * @param episodeNum episode number
	 * @param lengthOfEpisode total watch time of the episode
	 */
	public Episode(String episodeTitle, int seasonNum, int episodeNum, String lengthOfEpisode){
		this.setEpisodeTitle(episodeTitle);
		this.setSeasonNum(seasonNum);
		this.setSeasonNum(seasonNum);
		this.setLengthOfEpisode(new Time(lengthOfEpisode));
		this.setEpisodeNum(episodeNum);
		watched = false;
	}
	/**
	 * @return the episodeTitle
	 */
	public String getEpisodeTitle() {
		return episodeTitle;
	}
	/**
	 * @param episodeTitle the episodeTitle to set
	 */
	public void setEpisodeTitle(String episodeTitle) {
		this.episodeTitle = episodeTitle;
	}
	/**
	 * @return the seasonNum
	 */
	public int getSeasonNum() {
		return seasonNum;
	}
	/**
	 * @param seasonNum the seasonNum to set
	 */
	public void setSeasonNum(int seasonNum) {
		this.seasonNum = seasonNum;
	}
	/**
	 * @return the episodeNum
	 */
	public int getEpisodeNum() {
		return episodeNum;
	}
	/**
	 * @param episodeNum the episodeNum to set
	 */
	public void setEpisodeNum(int episodeNum) {
		this.episodeNum = episodeNum;
	}
	/**
	 * @return the watched
	 */
	public boolean isWatched() {
		return watched;
	}
	/**
	 * @param watched the watched to set
	 */
	public void setWatched(boolean watched) {
		this.watched = watched;
	}
	/**
	 * @return the lengthOfEpisode
	 */
	public Time getLengthOfEpisode() {
		return lengthOfEpisode;
	}
	/**
	 * @param lengthOfEpisode the lengthOfEpisode to set
	 */
	public void setLengthOfEpisode(Time lengthOfEpisode) {
		this.lengthOfEpisode = lengthOfEpisode;
	}
	/**
	 * 
	 * @param title
	 * @param seasonNum
	 * @param episodeNum
	 */
	public void displayEpisode(String title,String  seasonNum,String  episodeNum) {
		
	}
	/**
	 * 
	 */
	public void setEpisodeWatched(boolean watched){
		this.watched = watched;
	}
	@Override
	public int compareTo(Episode o) {
		if (episodeTitle.equals(o.getEpisodeTitle())) {
			if (seasonNum == o.getSeasonNum()) {
				if ( episodeNum - o.episodeNum == 0) {
					return 0;
				}else {
					return episodeNum - o.episodeNum;
				}
			}else {
				return seasonNum - o.getSeasonNum();
			}
			
		}else {
			return episodeTitle.compareTo(o.getEpisodeTitle());
		}
		
	}
	
	public static class SortByEpisodeNum implements Comparator<Episode>{

		@Override
		public int compare(Episode o1, Episode o2) {
			return o1.getEpisodeNum() - o2.getEpisodeNum();
		}
		
	}
	
	public static class SortByEpisodeTitle implements Comparator<Episode>{

		@Override
		public int compare(Episode o1, Episode o2) {
			
			return o1.getEpisodeTitle().compareTo(o2.getEpisodeTitle());
		}
		
	}
	
	public static class SortByEpisodeTime implements Comparator<Episode>{

		@Override
		public int compare(Episode o1, Episode o2) {
			if (o1.getLengthOfEpisode().getNumOfHours() - o2.getLengthOfEpisode().getNumOfHours() == 0) {
				if (o1.getLengthOfEpisode().getNumOfMinutes() - o2.getLengthOfEpisode().getNumOfMinutes() == 0) {
					if (o1.getLengthOfEpisode().getNumOfSeconds() - o2.getLengthOfEpisode().getNumOfSeconds() == 0) {
						return 0;
					}else {
						return o1.getLengthOfEpisode().getNumOfSeconds() - o2.getLengthOfEpisode().getNumOfSeconds();
					}
				}else {
					return o1.getLengthOfEpisode().getNumOfMinutes() - o2.getLengthOfEpisode().getNumOfMinutes();
				}
			}else {
				return o1.getLengthOfEpisode().getNumOfHours() - o2.getLengthOfEpisode().getNumOfHours();
			}
		}
		
	}
	
}
