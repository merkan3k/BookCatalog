package com.bookworld.UI;

import com.bookworld.Enitity.Book;
import com.bookworld.Service.BookService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;




/**
 * Created by vova on 08.12.2016.
 */

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private BookService service;

    private Book book;

    private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    // Image as a file resource
    FileResource resource = new FileResource(new File(basepath + "/img/old_books.jpg"));

    private Grid grid = new Grid();
    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private TextField year = new TextField("Year");
    private TextField price = new TextField("Price");
    private TextField insertTitle = new TextField("Title");
    private TextField insertAuthor = new TextField("Author");
    private TextField insertYear = new TextField("Year");
    private TextField insertPrice = new TextField("Price, $");
    private Button save = new Button("Save", e -> saveBook());
    private Button insert = new Button("Insert", e -> insertBook());
    private Button addBook = new Button("Add new book", e -> setInsertFormVisible(true));
    private Image image = new Image("It can make you stronger!", resource);


    @Override
    protected void init(VaadinRequest request) {
        updateGrid();
        grid.setColumns("title", "author", "year", "price");
        grid.addSelectionListener(e -> updateForm());

        VerticalLayout rootLayout = new VerticalLayout();
        setContent(rootLayout);

        Label bookLabel = new Label("Choose your books");
        bookLabel.setStyleName("h1");
        HorizontalLayout labelLayout = new HorizontalLayout(bookLabel);
        labelLayout.setMargin(true);
        rootLayout.addComponent(labelLayout);

        VerticalLayout gridLayout = new VerticalLayout(addBook, grid);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);


        VerticalLayout rigthLayout = new VerticalLayout(image);
        rigthLayout.setMargin(true);
        rigthLayout.setSpacing(true);

        HorizontalLayout topLayout = new HorizontalLayout(gridLayout, rigthLayout);
        rootLayout.addComponent(topLayout);


        HorizontalLayout buttonLayout = new HorizontalLayout(title, author, year, price, insertTitle, insertAuthor, insertYear,
                insertPrice, save, insert);
        buttonLayout.setMargin(true);
        buttonLayout.setSpacing(true);
        rootLayout.addComponent(buttonLayout);

    }

    private void updateGrid() {
        List<Book> books = service.findAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Book.class,
                books));
        setFormVisible(false);
        setInsertFormVisible(false);
    }

    private void updateForm() {
        if (grid.getSelectedRows().isEmpty()) {
            setFormVisible(false);
            setInsertFormVisible(false);
        } else {
            book = (Book) grid.getSelectedRow();
            BeanFieldGroup.bindFieldsUnbuffered(book, this);
            setFormVisible(true);
        }
    }

    private void setFormVisible(boolean visible) {
        if(visible) {
            setInsertFormVisible(false);
        }
        title.setVisible(visible);
        author.setVisible(visible);
        year.setVisible(visible);
        price.setVisible(visible);
        save.setVisible(visible);

    }

    private void saveBook() {
        service.update(book);
        updateGrid();
    }

    private void setInsertFormVisible(boolean visible) {
        if(visible) {
            setFormVisible(false);
        }
        insertTitle.setVisible(visible);
        insertAuthor.setVisible(visible);
        insertYear.setVisible(visible);
        insertPrice.setVisible(visible);
        insert.setVisible(visible);
    }

    private void insertBook() {
        String titleValue = insertTitle.getValue();
        String authorValue = insertAuthor.getValue();
        int yearValue = Integer.valueOf(insertYear.getValue());
        String str = insertPrice.getValue().replaceAll(",","");
        BigDecimal bd = new BigDecimal(str);
        Book book = new Book(titleValue, authorValue, yearValue, bd);
        service.insertBook(book);
        setInsertFormVisible(false);
        updateGrid();
    }

}
