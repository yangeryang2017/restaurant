package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import db.MongoDBConnection;

/**
 * Servlet implementation class FilterRestaurants. Currently only available in MongoDB version.
 */
@WebServlet("/filter")
public class FilterRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MongoDBConnection connection = new MongoDBConnection();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterRestaurants() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray array = null;
		if (request.getParameterMap().containsKey("term")) {
			String term = request.getParameter("term");
			String userId = "1111";
			array = connection.filterRestaurants(userId, term);
		}
		RpcParser.writeOutput(response, array);
	}
}
