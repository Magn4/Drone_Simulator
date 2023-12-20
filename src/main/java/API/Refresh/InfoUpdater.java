/* This class needs to get the latest info stored in the LatestInfo.json file and compare it with infos
* that will be fetched from the DroneDynamics Endpoint: "https://dronesim.facets-labs.com/api/dronedynamics/?limit=10"
* and see if there is any changes, and update the LatestInfo.json file. This Class needs to be run every
* 10 minutes (This time needs to be updated based on a test, of at what tempo does the data update, 
* that still needs to be done).
*/

package API.Refresh;

public class InfoUpdater {

    FileWriterUtil.writeToFile(responseContent.toString(), filePath);
}
