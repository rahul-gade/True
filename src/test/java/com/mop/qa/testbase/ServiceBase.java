package com.mop.qa.testbase;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mop.qa.Utilities.ExtentUtility;
import com.mop.qa.Utilities.ReadDataSheet;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This is the base class for accessing all Services
 * @author SAT
 *
 */
public class ServiceBase {
	final static Logger LOGGER = Logger.getLogger(ServiceBase.class);
	public String currentTest = "";
	public static String templateFolder = "Template/";
	public static String requestFolder = "Request/";
	public static String responseFolder = "Response/";
	public static String totalTimeTaken;
	public String accessToken;
	public String instanceURL;
	public String salesforceUser;
	public String requestFilePath = requestFolder + "Request__" + currentTest + ".json";
	public String responseFilePath = responseFolder + "Response_" + currentTest + ".json";
	public String apiURL = getPropertyValue("API");
	public String requestTemplatePath = templateFolder;
	public String accountID;
	public String accountName;
	public ReadDataSheet readData = new ReadDataSheet();
	public Map<String, String> queryParams = new HashMap<>();
	public Map<String, String> bodyParams = new HashMap<>();

	/**
	 * This method retrieves value from property file base on key
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key) {
		String value = "";
		try {

			InputStream fileInputStream = Files.newInputStream(Paths.get("data.properties"));

			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * This method updates property file with given input
	 * @param updateTime
	 * @param startTime
	 */
	public static void updateProperty(String updateTime, String startTime) {
		try {

			InputStream inputStream = Files.newInputStream(Paths.get("report.properties"));
			Properties props = new Properties();
			props.load(inputStream);
			inputStream.close();

			OutputStream out = Files.newOutputStream(Paths.get("report.properties"));
			props.setProperty("TOTAL_TIME", totalTimeTaken.toString());
			props.setProperty("RUN_STARTED", startTime.toString());
			props.store(out, null);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method gets InstanceURL And Token
	 * @throws Exception
	 */
	public void getInstanceURLAndToken() throws Exception {

		String baseURL = getPropertyValue("baseURL");
		String accessTokenURL = getPropertyValue("accessTokenURL");
		String sUserName = getPropertyValue("sUserName");
		String sPassword = getPropertyValue("sPassword");
		String clientID = getPropertyValue("clientID");
		String clientSecret = getPropertyValue("clientSecret");
		String grantType = getPropertyValue("grant_type");

		String serviceUrl = baseURL + accessTokenURL + "?username=" + sUserName + "&password=" + sPassword
				+ "&client_id=" + clientID + "&client_secret=" + clientSecret + "&grant_type=" + grantType;
		RequestSpecBuilder builder = new RequestSpecBuilder();
		RequestSpecification specification = builder.build();

		RequestSpecification specWithoutBody = given().contentType(ContentType.JSON).accept(ContentType.JSON).log()
				.all();
		specification = specWithoutBody;
		Response tokenResponse = specification.when().post(serviceUrl);

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("response for access token ---" + tokenResponse.getStatusCode());
		}

		JSONObject createTestResp = new JSONObject(tokenResponse.body().print());
		accessToken = createTestResp.getString("access_token");
		instanceURL = createTestResp.getString("instance_url");
		salesforceUser = sUserName;

	}

	/**
	 * @param requestType
	 * @param api
	 * @param queryParams
	 * @param requestFilePath
	 * @param responseFilePath
	 * @return
	 * @throws IOException
	 */
	public Response executeAPI(String requestType, String api, Map<String, String> queryParams, String requestFilePath,
			String responseFilePath) throws IOException {
		Response response = null;

		try {
			RequestSpecBuilder builder = new RequestSpecBuilder();
			RequestSpecification specification = builder.build();

			String requestBody = getJsonFile(requestFilePath);

			RequestSpecification specWithBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.queryParams(queryParams).body(requestBody).log().all();
			RequestSpecification specWithoutBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.queryParams(queryParams).log().all();

			// System.out.println("Request--" + requestBody);
			if (requestBody.equals("{}")) {
				specification = specWithoutBody;
			} else {
				specification = specWithBody;
			}
			switch (requestType.toUpperCase()) {
			case "GET":

				response = specification.when().get(api);
				break;

			case "POST":

				response = specification.when().post(api);
				break;

			case "PUT":

				response = specification.when().put(api);
				break;

			case "DELETE":

				response = specification.when().delete(api);
				break;

			case "PATCH":

				response = specification.when().patch(api);
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Response for " + requestType + " is received Successfully",
					"");

			if (requestType.toUpperCase() != "PUT" || requestType.toUpperCase() != "PATCH"
					|| requestType.toUpperCase() != "DELETE") {
				this.responseFilePath = responseFilePath;
				storeResponseInFile(responseFilePath, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @param requestType
	 * @param api
	 * @param requestString
	 * @param headerParams
	 * @param scenarioName
	 * @return
	 * @throws IOException
	 */
	public Response executeAPI(String requestType, String api, String requestString, Map<String, String> headerParams,
			String scenarioName) throws IOException {
		Response response = null;

		try {
			RequestSpecBuilder builder = new RequestSpecBuilder();
			RequestSpecification specification = builder.build();

			RequestSpecification specWithBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.headers(headerParams).queryParams(queryParams).body(requestString).log().all();
			RequestSpecification specWithoutBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.queryParams(queryParams).headers(headerParams).log().all();

			// System.out.println("Request--" + requestBody);
			if (requestString.equals("{}") || requestString.isEmpty()) {
				specification = specWithoutBody;
			} else {
				specification = specWithBody;
			}
			switch (requestType.toUpperCase()) {
			case "GET":

				response = specification.when().get(api);
				break;

			case "POST":

				response = specification.when().post(api);
				break;

			case "PUT":

				response = specification.when().put(api);
				break;

			case "DELETE":

				response = specification.when().delete(api);
				break;

			case "PATCH":

				response = specification.when().patch(api);
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Response for " + requestType + " is received ",
					scenarioName + " Successfully");
			LOGGER.info("Service Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @param requestType
	 * @param api
	 * @param requestString
	 * @param headerParams
	 * @param query
	 * @param scenarioName
	 * @return
	 * @throws IOException
	 */
	public Response executeAPI(String requestType, String api, String requestString, Map<String, String> headerParams,
			String query, String scenarioName) throws IOException {
		Response response = null;

		try {
			RequestSpecBuilder builder = new RequestSpecBuilder();
			RequestSpecification specification = builder.build();

			RequestSpecification specWithBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.headers(headerParams).queryParams("swid", query).body(requestString).log().all();
			RequestSpecification specWithoutBody = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.queryParams(queryParams).headers(headerParams).log().all();

			// System.out.println("Request--" + requestBody);
			if (requestString.equals("{}") || requestString.isEmpty()) {
				specification = specWithoutBody;
			} else {
				specification = specWithBody;
			}
			switch (requestType.toUpperCase()) {
			case "GET":

				response = specification.when().get(api);
				break;

			case "POST":

				response = specification.when().post(api);
				break;

			case "PUT":

				response = specification.when().put(api);
				break;

			case "DELETE":

				response = specification.when().delete(api);
				break;

			case "PATCH":

				response = specification.when().patch(api);
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Response for " + requestType + " is received ",
					scenarioName + " Successfully");
			LOGGER.info("Service Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public void oAuth2(String url, String userName, String accessToken) {
		baseURI = url;
		authentication = oauth2(accessToken);

		if (!(baseURI.length() > 0)) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Invalid API call. Failed to Authenticate " + userName,
					baseURI);
		} else {
			ExtentUtility.getTest().log(LogStatus.PASS, "Authentication successful for " + userName, baseURI);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(authentication + " " + userName);
		}
	}

	/**
	 * @param requestTemplatePath
	 * @param bodyParams
	 * @return
	 * @throws IOException
	 */
	public String generateRequestBody(String requestTemplatePath, Map<String, String> bodyParams) throws IOException {
		String requestString = null;
		try {
			// System.out.println("TemplatePath--" + requestTemplatePath);
			requestString = getTemplate(requestTemplatePath);

			requestString = setJSONValue(requestString, bodyParams);

			LOGGER.info(requestString);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return requestString;
	}

	/**
	 * @param response
	 */
	public void httpStatusCodeValidation(Response response) {

		int statusCode = response.getStatusCode();
		String codes = String.valueOf(statusCode);
		if (codes != null && !codes.isEmpty()) {
			if ((statusCode >= 100) && (statusCode < 200)) {
				ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code verified", String.valueOf(statusCode));
				// System.out.println("Code received for Request");
			} else if ((statusCode >= 200) && (statusCode < 300)) {
				// System.out.println("statuscodevalidation Successful");
				switch (statusCode) {
				case 200:
					ExtentUtility.getTest().log(LogStatus.PASS, "Status Code Validated - OK",
							String.valueOf(statusCode));
					break;

				case 201:
					ExtentUtility.getTest().log(LogStatus.PASS, "Status Code Validated - Created",
							String.valueOf(statusCode));
					break;

				case 202:
					ExtentUtility.getTest().log(LogStatus.PASS, "Status Code Validated - Accepted",
							String.valueOf(statusCode));
					break;
				}
			} else if ((statusCode >= 300) && (statusCode < 400)) {
				ExtentUtility.getTest().log(LogStatus.INFO, "Status Code verified", String.valueOf(statusCode));
				LOGGER.info("Redirection");
			} else if ((statusCode >= 400) && (statusCode < 500)) {
				ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code verified", String.valueOf(statusCode));
				LOGGER.info("Client Error");

				switch (statusCode) {
				case 400:
					ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code Validated - Bad Request",
							String.valueOf(statusCode));
					break;

				case 401:
					ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code Validated - Unauthorized",
							String.valueOf(statusCode));
					break;

				case 403:
					ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code Validated - Forbidden",
							String.valueOf(statusCode));
					break;

				case 404:
					ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code Validated - Not Found",
							String.valueOf(statusCode));
					break;

				case 407:
					ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code Validated - Proxy Authentication Required",
							String.valueOf(statusCode));
					break;
				}
			} else if ((statusCode >= 500) && (statusCode < 600)) {
				ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code verified", String.valueOf(statusCode));
				LOGGER.info("Server Error");
			}
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code not reveived", String.valueOf(statusCode));
		}
	}

	/**
	 * @param response
	 * @param value
	 */
	public void httpStatusCodeValidation(Response response, String value) {

		int statusCode = response.getStatusCode();
		int checkStatusVal = Integer.parseInt(value);
		String codes = String.valueOf(statusCode);
		if (codes != null && !codes.isEmpty()) {
			if (checkStatusVal == statusCode) {
				ExtentUtility.getTest().log(LogStatus.PASS,
						"Status Code validation successful. <br /> Expected Value : " + value,
						"Actual Value : " + String.valueOf(statusCode));
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"Status Code assertion unsuccessful. <br /> Expected Value : " + value,
						"Actual Value : " + String.valueOf(statusCode));
			}
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Status Code not reveived", String.valueOf(statusCode));
		}
	}

	/**
	 * @param response
	 * @param key
	 * @param value
	 */
	public void validateHeader(Response response, String key, String value) {

		// System.out.println("Inside validateheader");
		Headers allHeaders = response.headers();

		// for(Header header : allHeaders) {
		// System.out.println("Key: " + header.getName() + " Value: " +
		// header.getValue());
		// }

		String head = allHeaders.get(key).getName();
		String valuefield = allHeaders.get(key).getValue();
		// System.out.println("head--" + head + " value--" + valuefield);

		if (key.equals(head))
			ExtentUtility.getTest().log(LogStatus.PASS, "Key parameter verified <br />" + "Expected Header : " + key,
					"Actual Header : " + head);
		else
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"Key parameter verification failed <br />" + "Expected Header : " + key, "Actual Header : " + head);
		if (valuefield.equals(value))
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Value parameter verified <br />" + "Expected HeaderValue : " + value,
					"Actual HeaderValue : " + valuefield);
		else
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"Value parameter verification failed <br />" + "Expected HeaderValue : " + value,
					"Actual HeaderValue : " + valuefield);

	}

	/**
	 * @param documentContext
	 * @param testcaseName
	 * @param response
	 */
	public void validateResponse(DocumentContext documentContext, String testcaseName, Response response) {

		HSSFWorkbook wb;
		HSSFSheet ws;
		String valueType = "";

		try {

			InputStream file = Files.newInputStream(Paths.get("./DataSheet.xls"));
			wb = new HSSFWorkbook(file);
			ws = wb.getSheet("ResponseValidation");
			for (int count = 1; count <= ws.getLastRowNum(); count++) {
				HSSFRow row = ws.getRow(count);
				if (row.getCell(0).toString().equalsIgnoreCase(testcaseName)) {
					int last = row.getLastCellNum();
					for (int i = 1; i < last; i = i + 4) {
						String assertionType = row.getCell(i).toString();
						String jsonPath = row.getCell(i + 1).toString();
						if (row.getCell(i + 1) != null) {
							valueType = row.getCell(i + 2).toString();
						}
						String value = row.getCell(i + 3).toString();

						// System.out.println("jsonpath----- " + jsonPath + "
						// type--------- " +
						// valueType
						// + " value------------ " + value);

						switch (assertionType) {

						case "Header":
							validateHeader(response, jsonPath, value);
							break;

						case "Element_Occurrence":
							if (valueType.equalsIgnoreCase("key")) {
								occurenceKeyCount(documentContext, value, responseFilePath);
							} else if (valueType.equalsIgnoreCase("value")) {
								occurenceValueCount(documentContext, value);
							}
							break;

						case "Element_Value":
							validateContent(documentContext, jsonPath, valueType, value);
							break;

						case "Contains_Value":
							containsString(documentContext, jsonPath, value);
							break;

						case "Content_Presence":
							contentPresence(documentContext, jsonPath, value);
							break;

						case "StatusCode":
							httpStatusCodeValidation(response, value);
							break;

						default:
							LOGGER.info("Invalid validation");

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param documentContext
	 * @param jsonPath
	 * @param value
	 */
	public void containsString(DocumentContext documentContext, String jsonPath, String value) {
		Object responseObj = getJSONValue(documentContext, jsonPath);
		String responseVal = String.valueOf(responseObj);

		if (responseVal.contains(value)) {
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Response contains the String in the path <br /> Expected Value :" + value,
					"Actual Value : " + responseVal);
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"String unavailable in the path <br /> Expected Value :" + value, "Actual Value : " + responseVal);
		}

	}

	/**
	 * @param documentContext
	 * @param jsonPath
	 * @param valueType
	 * @param value
	 */
	public void validateContent(DocumentContext documentContext, String jsonPath, String valueType, String value) {
		Object res;
		String rs;
		int val;
		long lval;
		int values;
		long lvalues;
		float mat, matfloat;
		switch (valueType.toLowerCase()) {
		case "string":
			res = getJSONValue(documentContext, jsonPath);
			rs = String.valueOf(res);
			if (value.equalsIgnoreCase(rs)) {
				ExtentUtility.getTest().log(LogStatus.PASS,
						"String value verified <br />" + "Expected value : " + value, "Actual value : " + rs);
				// System.out.println("STRING MATCHED");
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"String value verification failed <br />" + "Expected value : " + value,
						"Actual value : " + rs);
			}
			break;
		case "integer":
		case "int":
			res = getJSONValue(documentContext, jsonPath);
			rs = String.valueOf(res);
			val = Integer.valueOf(rs);// Integer.parseInt(rs);
			if (isInteger(value)) {
				values = Integer.valueOf(value);// Integer.parseInt(value);
				// System.out.println("val--" + val + " values--" + values);
				if (val == values) {
					// Assert.assertEquals("integer", valueType.toLowerCase());
					ExtentUtility.getTest().log(LogStatus.PASS,
							"Integer value verified <br />" + "Expected value : " + value, "Actual value : " + val);
					// System.out.println("INTEGER MATCHED");
				} else {
					ExtentUtility.getTest().log(LogStatus.FAIL,
							"Integer value verification failed <br />" + "Expected value : " + value,
							"Actual value : " + val);
				}
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"Integer value verification failed <br />" + "Expected value : " + value,
						"Actual value is not Integer type");
			}

			break;

		case "long":
			res = getJSONValue(documentContext, jsonPath);
			rs = String.valueOf(res);
			lval = Long.valueOf(rs);// Integer.parseInt(rs);
			if (isLong(value)) {
				lvalues = Long.parseLong(value);// Integer.parseInt(value);
				// System.out.println("val--" + lval + " values--" + lvalues);
				if (lval == lvalues) {
					// Assert.assertEquals("integer", valueType.toLowerCase());
					ExtentUtility.getTest().log(LogStatus.PASS,
							"Long value verified <br />" + "Expected value : " + value, "Actual value : " + lval);
					LOGGER.info("Long MATCHED");
				} else {
					ExtentUtility.getTest().log(LogStatus.FAIL,
							"Long value verification failed <br />" + "Expected value : " + value,
							"Actual value : " + lval);
				}
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"Long value verification failed <br />" + "Expected value : " + value,
						"Actual value is not Long type");
			}

			break;

		case "float":
			res = getJSONValue(documentContext, jsonPath);
			rs = String.valueOf(res);
			matfloat = Float.parseFloat(rs);
			if (isFloat(value)) {
				mat = Float.parseFloat(value);
				if (mat == matfloat) {
					// Assert.assertEquals("float", valueType.toLowerCase());
					ExtentUtility.getTest().log(LogStatus.PASS,
							"Float value verified <br />" + "Expected value : " + value, "Actual value : " + mat);
					LOGGER.info("FLOAT MATCHED");
				} else {
					ExtentUtility.getTest().log(LogStatus.FAIL,
							"Float value verification failed <br />" + "Expected value : " + value,
							"Actual value : " + mat);
				}
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"Float value verification failed <br />" + "Expected value : " + value,
						"Actual value is not Float type");
			}

			break;
		}

	}

	/**
	 * @param s
	 * @return
	 */
	public boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);
			isValidInteger = true;
		} catch (NumberFormatException ex) {
		}
		return isValidInteger;
	}

	/**
	 * @param s
	 * @return
	 */
	public boolean isLong(String s) {
		boolean isValidLong = false;
		try {
			Long.parseLong(s);
			isValidLong = true;
		} catch (NumberFormatException ex) {
		}
		return isValidLong;
	}

	/**
	 * @param s
	 * @return
	 */
	public boolean isFloat(String s) {
		boolean isValidFloat = false;
		try {
			Float.parseFloat(s);

			isValidFloat = true;
		} catch (NumberFormatException ex) {
		}

		return isValidFloat;
	}

	/**
	 * getJSONValue fetches the value present in the JSONpath
	 *
	 * @param doc
	 *            DocumentContext parsed from a json string
	 * @param jsonpath
	 *            Path of key for which the value has to be fetched
	 *
	 * @return JSON as Object
	 *
	 */
	public Object getJSONValue(DocumentContext doc, String jsonpath) {
		// System.out.println("Get JSONValue-" + jsonpath + " =
		// doc.read(jsonpath)-" +
		// doc.read(jsonpath));
		return doc.read(jsonpath);
	}

	/**
	 * setJSONValue stores the value present in the JSONpath
	 *
	 * @param jsonString
	 *            JSON passed as String
	 * @param jsonpath
	 *            Path of key for which the value has to be fetched
	 * @param newValue
	 *            New Value to be replaced with in the JSONpath
	 *
	 * @return JSON as String
	 *
	 */
	public String setJSONValue(String jsonString, Map<String, String> bodyParams) {
		String json = jsonString;
		try {
			for (Map.Entry<String, String> m : bodyParams.entrySet()) {
				String jsonPath = m.getKey();
				String newValue = m.getValue();
				LOGGER.info("before doc context");
				DocumentContext initialDoc = null;
				try {
					initialDoc = JsonPath.parse(json);
				} catch (Exception e) {
					LOGGER.info(e);
				}
				String currentValue = initialDoc.read(jsonPath);
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("Currenttt------" + currentValue);
				}
				DocumentContext doc = JsonPath.parse(json).set(jsonPath, newValue);
				json = doc.jsonString();
				if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("jsonPath-" + jsonPath + " newValue-" + newValue);
				}
				
				if (!jsonString.equals(json)) {
					ExtentUtility.getTest().log(LogStatus.PASS,
							"Value modified in JSON Object <br /> Existing Value : " + currentValue,
							"New Value : " + newValue);
				} else if (jsonString.contains(newValue)) {
					ExtentUtility.getTest().log(LogStatus.INFO, "Value exists, Unable to modify value in JSON Object",
							"");
				} else {
					ExtentUtility.getTest().log(LogStatus.FAIL, "Unable to modify value in JSON Object", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * @param requestTemplatePath
	 * @return
	 * @throws IOException
	 */
	public String getTemplate(String requestTemplatePath) throws IOException {
		File file = new File(requestTemplatePath);
		if (!(file.exists())) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Template File not found in path", file.getAbsolutePath());
			return null;
		} else if (requestTemplatePath.isEmpty() || requestTemplatePath.equals("")) {
			ExtentUtility.getTest().log(LogStatus.INFO, "Template File is Empty. Assigning default body for request.",
					"{}");
			return "{}";
		}
		String fileContent = new String(Files.readAllBytes(Paths.get(requestTemplatePath)));
		// System.out.println("fileContent- " + fileContent + "
		// requestTemplatePath--" +
		// requestTemplatePath);
		if (fileContent.contains("{}") || fileContent.length() > 2) {
			ExtentUtility.getTest().log(LogStatus.PASS, "Fetching Template file successful",
					"<a href=\"" + file.getAbsolutePath() + "\">" + requestTemplatePath.split("/")[1] + "</a>");
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Fetching Template from file unsuccessful", "");
		}
		return fileContent;
	}

	/**
	 * getJsonFile fetches the JSON from a file
	 *
	 * @param requestFilePath
	 *            Path of JSON file
	 *
	 * @return JSON as String
	 *
	 */
	public String getJsonFile(String requestFilePath) throws IOException {
		File file = new File(requestFilePath);
		if (!(file.exists())) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "File not found in path", file.getAbsolutePath());
			return null;
		} else if (requestFilePath.isEmpty() || requestFilePath.equals("")) {
			ExtentUtility.getTest().log(LogStatus.INFO, "No request Body for the request", "{}");
			return "{}";
		}
		String fileContent = new String(Files.readAllBytes(Paths.get(requestFilePath)));
		// System.out.println("fileContent- " + fileContent + "
		// requestFilePath--" +
		// requestFilePath);
		/*
		 * if (fileContent.contains("{}") || fileContent.length() > 2) {
		 * ExtentUtility.getTest().log(LogStatus.PASS,
		 * "Fetching Request file successful", "<a href=\"" + file.getAbsolutePath() +
		 * "\">" + requestFilePath.split("/")[1] + "</a>"); } else {
		 * ExtentUtility.getTest().log(LogStatus.FAIL,
		 * "Fetching Request from file unsuccessful", ""); }
		 */
		return fileContent;
	}

	/**
	 * storeJsonInFile stores the JSON from a file
	 *
	 * @param jsonFilePath
	 *            Path of JSON file
	 * @param jsonString
	 *            JSON Response to be stored
	 *
	 * @return File Write status flag
	 *
	 */
	public boolean storeJsonInFile(String jsonFilePath, String jsonString) {
		File file = new File(jsonFilePath);
		try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(jsonFilePath), StandardCharsets.UTF_8)) {
			fileWriter.write(jsonString);
			fileWriter.flush();
			// System.out.println("File Write Complete--" + jsonFilePath);
			ExtentUtility.getTest().log(LogStatus.PASS,
					"JSON stored into file <br />" + jsonFilePath.split("/")[1] + " file",
					"<a href=\"" + file.getAbsolutePath() + "\">" + jsonFilePath.split("/")[1] + "</a>");
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("File Write Failed");
			ExtentUtility.getTest().log(LogStatus.FAIL, "Failed to capture JSON in a file");
		}
		return false;
	}

	/**
	 * @param responseFilePath
	 * @param response
	 * @return
	 */
	public boolean storeResponseInFile(String responseFilePath, Response response) {
		File file = new File(responseFilePath);
		String responseString = formatResponse(response);
		try (BufferedWriter fileWriter = Files.newBufferedWriter(Paths.get(responseFilePath), StandardCharsets.UTF_8)) {
			fileWriter.write(responseString);
			fileWriter.flush();
			// System.out.println("File Write Complete--" + responseFilePath);
			ExtentUtility.getTest().log(LogStatus.PASS,
					"JSON stored into file <br />" + responseFilePath.split("/")[1] + " file",
					"<a href=\"" + file.getAbsolutePath() + "\">" + responseFilePath.split("/")[1] + "</a>");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("File Write Failed");
			ExtentUtility.getTest().log(LogStatus.FAIL, "Failed to capture JSON in a file");
		}
		return false;
	}

	/**
	 * @param response
	 * @return
	 */
	public String formatResponse(Response response) {
		String format = response.body().prettyPrint();
		return format;
	}

	/**
	 * @param response
	 * @param intervals
	 */
	public void assertResponseTime(Response response, int intervals) {
		long time = response.getTimeIn(TimeUnit.SECONDS);
		if (time <= intervals) {
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Response time is Successfully validated <br />" + "Expected value : " + intervals,
					"Actual value : " + time);
			// System.out.println("time passed");
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"Response time is not validated <br />" + "Expected value : " + intervals,
					"Actual value : " + time);
			// System.out.println("time failed");
		}
	}

	/**
	 * @param doc
	 * @param jsonpath
	 * @return
	 */
	public int getJSONCount(DocumentContext doc, String jsonpath) {
		return doc.read(jsonpath);
	}

	/**
	 * Assertion for specific value presence in Response Received
	 * @param documentContext
	 * @param name
	 */
	public void occurenceValueCount(DocumentContext documentContext, String name) {
		String ash = ".*";
		int count = 0;

		ArrayList<String> list = new ArrayList<String>();
		// System.out.println("getJSON--- in occurenceval -- " +
		// getJSONValue(documentContext, "$.*"));
		String rs = String.valueOf(getJSONValue(documentContext, "$.*"));
		// System.out.println(rs);
		rs = rs.replace("[", "");
		rs = rs.replace("]", "");
		// System.out.println(rs);
		String[] r;
		String val = "";
		boolean flag = false;
		while (rs != null) {
			flag = false;
			r = rs.split(",");
			for (int j = 0; j < r.length; j++) {
				list.add(r[j]);
				// System.out.println(r[j]);
				val = r[j];
				val = val.replace("\"", "");
				// System.out.println(val);
				if (name.equalsIgnoreCase(val)) {
					count++;
				}
				if (val.contains("{")) {
					rs = String.valueOf(getJSONValue(documentContext, "$.*" + ash));
					rs = rs.replace("[", "");
					rs = rs.replace("]", "");
					// System.out.println(rs);
					flag = true;
					ash = ash + ".*";
					break;
				}
			}
			if (flag == false) {
				break;
			}

		}
		// System.out.println(count + " ---------count");
		ExtentUtility.getTest().log(LogStatus.PASS, "Number of occurences of " + name + " is " + count);
	}

	
	/**
	 * Assertion for specific key presence in Response Received
	 * @param documentContext
	 * @param key
	 * @param filePath
	 * @throws IOException
	 */
	public void occurenceKeyCount(DocumentContext documentContext, String key, String filePath) throws IOException {
		
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
		String line;
		int count1 = 0;
		while ((line = bufferedReader.readLine()) != null) {

			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replace("[", "");
			line = line.replace("]", "");
			line = line.replace("\"", "");
			line = line.replace(",", "");
			line = line.replace(" ", "");
			if (!line.equalsIgnoreCase("")) {
				// System.out.println(line);
				String lin = line.split(":")[0];
				if (lin.equalsIgnoreCase(key)) {
					count1++;
				}
			}
		}
		
		ExtentUtility.getTest().log(LogStatus.PASS, "Count of key occurence in response : ", String.valueOf(count1));
	}

	/**
	 * @param documentContext
	 * @param jsonpath
	 * @param containsString
	 */
	public void contentPresence(DocumentContext documentContext, String jsonpath, String containsString) {
		String content = String.valueOf(getJSONValue(documentContext, jsonpath));
		if (content.isEmpty() || content == null) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "No Content Available in the path : " + jsonpath,
					"Value : " + content);
		} else if (content.contains(containsString)) {
			ExtentUtility.getTest().log(LogStatus.PASS, containsString + " is Available in the path : " + jsonpath,
					"Value : " + content);
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, containsString + " is unavailable in the path : " + jsonpath,
					"Value : " + content);
		}
	}

	/**
	 * @param testcaseName
	 * @return
	 */
	public Map<String, String> getQueryParameters(String testcaseName) {
		Map<String, String> map = new HashMap<>();
		HSSFWorkbook wb;
		HSSFSheet ws;
		try {
			InputStream file = Files.newInputStream(Paths.get("./DataSheet.xls"));
			wb = new HSSFWorkbook(file);
			ws = wb.getSheet("RequestParameters");
			for (int count = 1; count <= ws.getLastRowNum(); count++) {
				HSSFRow row = ws.getRow(count);
				if (row.getCell(0).toString().equalsIgnoreCase(testcaseName)
						&& row.getCell(1).toString().equalsIgnoreCase("QueryParameter")) {
					map.put(row.getCell(2).toString(), row.getCell(3).toString());
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return map;
	}

	/**
	 * @param queryParams
	 * @return
	 */
	public String generateQueryParameters(Map<String, String> queryParams) {
		String query = "?";
		for (Map.Entry<String, String> param : queryParams.entrySet()) {
			query = query + param.getKey() + "=" + param.getValue() + "&";
		}

		return query;
	}

	/**
	 * @param testcaseName
	 * @return
	 */
	public Map<String, String> getBodyParameters(String testcaseName) {
		Map<String, String> map = new HashMap<>();
		HSSFWorkbook wb;
		HSSFSheet ws;
		try {
			InputStream file = Files.newInputStream(Paths.get("./DataSheet.xls"));
			wb = new HSSFWorkbook(file);
			ws = wb.getSheet("RequestParameters");
			for (int count = 1; count <= ws.getLastRowNum(); count++) {
				HSSFRow row = ws.getRow(count);
				if (row.getCell(0).toString().equalsIgnoreCase(testcaseName)
						&& row.getCell(1).toString().equalsIgnoreCase("BodyParameter")) {
					map.put(row.getCell(2).toString(), row.getCell(3).toString());
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return map;
	}

	/**
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getAppProperties(String key) throws IOException {
		String value = "";
		try {

			InputStream fileInputStream = Files.newInputStream(Paths.get("data.properties"));
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * @param response
	 * @param responseTime
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public boolean basicAssertions(Response response, int responseTime) throws NumberFormatException, IOException {
		if (isJSONValid(response.asString())) {
			int responseTimeNewVal = responseTime;
			if (responseTimeNewVal == 0)
				responseTimeNewVal = Integer.parseInt(getAppProperties("responseTime"));
			assertResponseTime(response, responseTimeNewVal);
			contentType(response);
			httpStatusCodeValidation(response);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param response
	 */
	public void contentType(Response response) {

		if (response.header("Content-Type").contains("json")
				|| response.header("Content-Type").equalsIgnoreCase("application/json")) {
			ExtentUtility.getTest().log(LogStatus.PASS, "Content-Type validated successfully.",
					"Actual header : " + response.header("Content-Type"));
		} else if (response.header("Content-Type").contains("xml")
				|| response.header("Content-Type").equalsIgnoreCase("application/xml")) {
			ExtentUtility.getTest().log(LogStatus.PASS, "Content-Type validated successfully.",
					"Actual header : " + response.header("Content-Type"));
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Failed to validate Content-Type.",
					"Actual header : " + response.header("Content-Type"));
		}

	}

	/**
	 * @param test
	 * @return
	 */
	public boolean isJSONValid(String test) {
		try {
			new org.json.JSONObject(test);
		} catch (JSONException ex) {
			try {
				new org.json.JSONArray(test);
			} catch (JSONException ex1) {
				ExtentUtility.getTest().log(LogStatus.FAIL, "Invalid JSON response");
				return false;
			}
		}
		ExtentUtility.getTest().log(LogStatus.PASS, "JSON response validated successfully");
		return true;
	}

}
