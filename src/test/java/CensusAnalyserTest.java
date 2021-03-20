import com.bridgelabz.censusanalyser.CensusAnalyser;
import com.bridgelabz.censusanalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CensusAnalyserTest {
    CensusAnalyserException analyserException;
    public static final String STATE_CENSUS_FILE="/home/tejas/IdeaProjects/CensusAnalyzer/src/main/resources/IndiaStateCensusData.csv";
    public static final String STATE_CODE_FILE="/home/tejas/IdeaProjects/CensusAnalyzer/src/main/resources/IndiaStateCode.csv";
    public static final String WRONG_FILE="/home/tejas/IdeaProjects/CensusAnalyzer/src/main/resources/IndiaStateCode.txt";

    @Test
    public void givenStateCensusCsvFile_ItHasCorrectNumber_ShouldMatchRecords() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords=censusAnalyser.loadCensusData(STATE_CENSUS_FILE);
        Assert.assertEquals(29,numOfRecords);
    }

    @Test
    public void givenStateCensusCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords=censusAnalyser.loadCensusData(STATE_CODE_FILE);
        }catch (CensusAnalyserException censusAnalyserException){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_ISSUE,censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_IfDoesntExists_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int noOfRecords = censusAnalyser.loadCensusData(WRONG_FILE);
        }catch (CensusAnalyserException censusAnalyserException){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE,censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusFile_WhenHeadersIncorrect_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int noOfRecords = censusAnalyser.loadCensusData(STATE_CODE_FILE);
        }catch (CensusAnalyserException censusAnalyserException){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_ISSUE,censusAnalyserException.type);
        }
    }
}