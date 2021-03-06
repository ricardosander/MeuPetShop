package br.com.ricardosander.meupetshop.servlets.pets;

import br.com.ricardosander.meupetshop.criteria.CriteriaBuilder;
import br.com.ricardosander.meupetshop.dao.MySQLPetDAO;
import br.com.ricardosander.meupetshop.dao.PetDAO;
import br.com.ricardosander.meupetshop.dao.PetDAOProvider;
import br.com.ricardosander.meupetshop.model.Pet;
import br.com.ricardosander.meupetshop.model.User;
import br.com.ricardosander.meupetshop.paginators.PaginatorView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/pets")
public class PetList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        String searchName = req.getParameter("searchName");

        CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
        if (searchName != null) {
            criteriaBuilder.name(searchName);
            req.setAttribute("searchName", searchName);
        }

        PetDAO petDAO = new PetDAOProvider().newPetDAO();
        int totalRegister = petDAO.count(loggedUser, criteriaBuilder.build());

        PaginatorView paginatorView = PaginatorView.from(req, totalRegister);

        criteriaBuilder
                .limit(paginatorView.getRegistersPerPage())
                .offset(paginatorView.getOffSet());

        List<Pet> pets = new PetDAOProvider().newPetDAO().find(loggedUser, criteriaBuilder.build());

        req.setAttribute("pets", pets);
        req.setAttribute("paginator", paginatorView);

        req.getRequestDispatcher("/WEB-INF/pages/pets/list.jsp").forward(req, resp);
    }
}
