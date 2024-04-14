package p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class wetherservelet
 */
@WebServlet("/wetherservelet")
public class wetherservelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wetherservelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		String city=request.getParameter("city");
		PrintWriter pw=response.getWriter();
		try {
			String wetherinfo=wetherapi(city);
			JsonObject data=json(wetherinfo);
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>insert</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<style>");
			pw.println("body ");
			pw.println("{");
			pw.println("  background-image: url('images/wether3.jpg');");
			pw.println("  background-size: cover;");
			pw.println(" background-position: center;");
			pw.println(" background-repeat: no-repeat;");
			pw.println("}");
			pw.println("</style>");
			pw.println("</head>");	
			pw.println("<body class='d-flex align-items-center justify-content-center vh-100' >");
			pw.println("<div class='container text-center'>");
			pw.println("<div class='border p-3 mx-auto rounded bg-light' style='width: 50%;'>");
			pw.println("<h1>wether_information</h1>");
			pw.println("</div>");
			
			pw.println("<div class='border p-3 mx-auto rounded bg-light' style='width: 50%;'>");
			pw.println("<div><b>location:</b>"+data.get("location")+"</div>");
			pw.println("<div><b>region:</b>"+data.get("state")+"</div>");
			pw.println("<div><b>country</b>"+data.get("place")+"</div>");
			pw.println("<div><b>localtime:</b>"+data.get("time")+"</div>");
			pw.println("<div><b>last_updated:</b>"+data.get("lastupdate")+"</div>");
			pw.println("<div><b>temp_c:</b>"+data.get("temperature")+"</div>");
			pw.println("<div><b>temp_f:</b>"+data.get("tempg")+"</div>");
			pw.println("<div><b>humidity:</b>"+data.get("a")+"</div>");
			pw.println("<div><b>cloud:</b>"+data.get("b")+"</div>");
			
			pw.println("</div>");
			pw.println("<div class='container'>");
			pw.println("<a href='wether.jsp' class='btn btn-primary' style='float: right;'>GO BACK</a>");
			pw.println("</div>");
			pw.print("</body>");
			pw.println("</html>");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			pw.println("<html>");
			pw.println("<head><title>insert</title></head>");
			pw.println("<body>");
			pw.println("<h1>error</h1>");
			pw.println("<p>"+e.getMessage()+"</P>");                                                                                                                                         
			pw.print("</body>");
			pw.println("</html>");
		}
		
	}
	public static String wetherapi(String city)throws Exception{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q="+city))
				.header("X-RapidAPI-Key", "765147c032msh752fc3fe8ffabd4p196a74jsn6bc4b3825784")
				.header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		String result=response.body();
		
		return result;
		
	}
	public static JsonObject json(String wetherinfo)throws Exception {
		JsonParser parser=new JsonParser();
		JsonObject obj=parser.parse(wetherinfo).getAsJsonObject();
		JsonObject data=new JsonObject();
		JsonObject locationdata=obj.getAsJsonObject("location");
		JsonObject currentdata=obj.getAsJsonObject("current");
		JsonObject conditiondata=obj.getAsJsonObject("condition");
		data.addProperty("location",locationdata.getAsJsonPrimitive("name").getAsString());
		data.addProperty("state", locationdata.getAsJsonPrimitive("region").getAsString());
		data.addProperty("place", locationdata.getAsJsonPrimitive("country").getAsString());
		data.addProperty("time", locationdata.getAsJsonPrimitive("localtime").getAsString());
		data.addProperty("lastUpdate", currentdata.getAsJsonPrimitive("last_updated").getAsString());
		data.addProperty("temperature", currentdata.getAsJsonPrimitive("temp_c").getAsDouble());
		data.addProperty("tempg", currentdata.getAsJsonPrimitive("temp_f").getAsDouble());
		if(conditiondata!=null) {
		data.addProperty("a", conditiondata.getAsJsonPrimitive("humidity").getAsString());
		data.addProperty("b", conditiondata.getAsJsonPrimitive("cloud").getAsString());
		}else {
			 data.addProperty("a", "N/A");
		        data.addProperty("b", "N/A");
		}
		
		//System.out.println(data);
		//return wetherinfo;		
		return data;
		
	}
	
	
}

	    
	
		

