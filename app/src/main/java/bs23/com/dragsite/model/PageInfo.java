
package bs23.com.dragsite.model;

public class PageInfo {

    private int totalResults;
    private int resultsPerPage;

    /**
     * 
     * @return
     *     The totalResults
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The totalResults
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * 
     * @return
     *     The resultsPerPage
     */
    public int getResultsPerPage() {
        return resultsPerPage;
    }

    /**
     * 
     * @param resultsPerPage
     *     The resultsPerPage
     */
    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

}
