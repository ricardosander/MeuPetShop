package br.com.ricardosander.meupetshop.servlets.pets;

import br.com.ricardosander.meupetshop.dao.PetDAO;
import br.com.ricardosander.meupetshop.dao.PetDAOProvider;
import br.com.ricardosander.meupetshop.model.Owner;
import br.com.ricardosander.meupetshop.model.Pet;
import br.com.ricardosander.meupetshop.model.User;
import br.com.ricardosander.meupetshop.validator.PetValidator;
import br.com.ricardosander.meupetshop.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/pets/register")
public class PetRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/pets/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> errors = new LinkedList<>();

        String name = req.getParameter("name");
        String species = req.getParameter("species");
        String breed = req.getParameter("breed");
        String fur = req.getParameter("fur");
        String pelage = req.getParameter("pelage");
        String mien = req.getParameter("mien");
        String weightString = req.getParameter("weight");
        String comments = req.getParameter("comments");
        String gender = req.getParameter("gender");
        String registerString = req.getParameter("register");
        String birthString = req.getParameter("birth");
        String castratedString = req.getParameter("castrated");
        String clientPacketString = req.getParameter("clientPacket");
        String ownerIdString = req.getParameter("owner_id");

        double weight = 0.0;
        try {
            weight = Double.parseDouble(weightString.replace(",", "."));
        } catch (Exception exception) {
            errors.add("O campo Peso deve ser um valor numérico válido.");
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate register = null;
        try {

            if (registerString != null && !registerString.trim().isEmpty()) {
                register = LocalDate.parse(registerString, dateTimeFormatter);
            }

        } catch (Exception exception) {
            errors.add("O campo Cadastro é de preenchimento obrigatório.");
        }

        LocalDate birth = null;
        try {

            if (birthString != null && !birthString.trim().isEmpty()) {
                birth = LocalDate.parse(birthString, dateTimeFormatter);
            }

        } catch (Exception exception) {
            errors.add("O campo Nascimento informado é inválido.");
        }

        boolean castrated = castratedString != null;

        boolean clientPacket = clientPacketString != null;

        long ownerId = 0;
        Owner owner;
        if (ownerIdString != null && !ownerIdString.trim().isEmpty()) {

            try {
                ownerId = Integer.parseInt(ownerIdString.trim());
            } catch (Exception exception) {
                //TODO tratar.
            }
            //TODO buscar owner.
        }

        User user = (User) req.getSession().getAttribute("loggedUser");

        Pet pet = new Pet(
                name,
                species,
                breed,
                fur,
                pelage,
                mien,
                weight,
                birth,
                register,
                castrated,
                comments,
                gender,
                clientPacket,
                user
        );


        Validator validator = new PetValidator(pet);
        try {
            validator.validate();
        } catch (InvalidObjectException exception) {
            errors.addAll(validator.getErrors());
        }

        if (!errors.isEmpty()) {
            //TODO add flash message.
            resp.sendRedirect("/pets/register");
            return;
        }

        PetDAO petDAO = new PetDAOProvider().newPetDAO();
        if (!petDAO.insert(pet)) {
            //TODO add flash message.
            errors.add("Houve um erro ao salvar o Pet.");
            resp.sendRedirect("/pets/register");
            return;
        }

        user.addFlashMessage("message", "Pet adicionado com sucesso.");
        resp.sendRedirect("/pets");
    }

}