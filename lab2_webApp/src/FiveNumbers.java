import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "fiveNumbers")
public class FiveNumbers extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int sumOfParameters  = 0;
        int parameterNumber = 5;

        //get all the parameter names
        Enumeration<String> params = request.getParameterNames();

        for(int i=0; (i<parameterNumber); i++){
            sumOfParameters += Integer
                    .parseInt(request.getParameter(params.nextElement()));

            System.out.println(sumOfParameters);
        }
        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();

        out.println((float) sumOfParameters/parameterNumber);

        out.close();
    }
}
