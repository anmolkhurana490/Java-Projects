import java.util.Scanner;
import java.lang.System;
class Book {
  int id=0;
  String name=null;
  String author=null;
  Book(String name,String author){
    this.name=name;
    this.author=author;
  }
}
class Library {
  int availTop=-1;
  int issuedTop=-1;
  int newid=101;
  int wasteTop=-1;
  Book []availBooks=new Book[100];
  Book []issuedBooks=new Book[100];
  int []wasteId=new int[10];
  
  Tools t=new Tools();
  void addBook(Book book){
    if(searchAvailBook(book)>=0){
      System.out.println("Book Already added!");
    }
    else if(availTop<availBooks.length-1){
      if(wasteTop>=0){
        book.id=wasteId[wasteTop--];
      }
      else {
        book.id=newid++;
      }
      availBooks[++availTop]=book;
      System.out.println("The Book is Added");
    }
    else {
      System.out.println("Can't Add! The Library is full");
    }
  }
  void removeBook(Book book){
    int p=searchAvailBook(book);
    if(p==-1 && searchIssuedBook(book)>=0){
      System.out.println("Can't be Removed! Book is issued");
    }
    if(p==-1){
      System.out.println("Book not Found!");
    }
    else {
      while(p<=availTop-1){
        availBooks[p]=availBooks[p+1];
        p++;
      }
      wasteId[++wasteTop]=availBooks[p].id;
      availBooks[p]=null;
      availTop--;
      book=null;
      System.out.println("The Book is Removed");
    }
  }
  void issueBook(Book book){
    if(availTop<0){
      System.out.println("Sorry! No Book is Available");
      return;
    }
    int i=searchAvailBook(book);
    if(i==-1){
      System.out.println("Book not Found!");
    }
    else {
      book=availBooks[i];
      while(i<=availTop){
        availBooks[i]=availBooks[i+1];
        i++;
      }
      wasteId[++wasteTop]=availBooks[i].id;
      availBooks[i]=null;
      availTop--;
      issuedBooks[++issuedTop]=book;
      System.out.println("The Book is Issued");
    }
  }
  void returnBook(Book book){
    if(issuedTop<0){
      System.out.println("Sorry! No Book is Issued");
      return;
    }
    int i=searchIssuedBook(book);
    if(i==-1){
      System.out.println("Book not Found!");
      return;
    }
    else {
      book=issuedBooks[i];
      while(i<=issuedTop){
        issuedBooks[i]=issuedBooks[i+1];
        i++;
      }
      wasteId[++wasteTop]=issuedBooks[i].id;
      issuedBooks[i]=null;
      issuedTop--;
      availBooks[++availTop]=book;
      System.out.println("The Book is Returned");
    }
  }
  void showAvailableBooks(){
    System.out.println("Available Books are");
    for(int i=0;i<=availTop;i++){
      System.out.printf("%d. %s (%d)\n",(i+1),availBooks[i].name,availBooks[i].id);
      System.out.println("\t"+availBooks[i].author);
    }
  }
  void showIssuedBooks(){
    System.out.println("Issued Books are");
    for(int i=0;i<=issuedTop;i++){
      System.out.printf("%d. %s (%d)\n",(i+1),issuedBooks[i].name,issuedBooks[i].id);
      System.out.printf("\t%s\n",issuedBooks[i].author);
    }
  }
  void searchBook(Book book){
    int index=searchAvailBook(book);
    Book b;
    if(index>=0){
      b=availBooks[index];
      System.out.println("The Book is Available");
      System.out.printf("Name: %s (%d) Author: %s\n",b.name,b.id,b.author);
      for(int i=index;i<=availTop;i++){
        if(t.equals(availBooks[i],book)){
          b=availBooks[i];
          System.out.printf("Name: %s (%d) Author: %s\n",b.name,b.id,b.author);
        }
      }
    }
    index=searchIssuedBook(book);
    if(index>=0){
      b=issuedBooks[index];
      System.out.println("The Book is Issued");
      System.out.printf("Name: %s (%d) Author: %s\n",b.name,b.id,b.author);
      for(int i=index;i<=issuedTop;i++){
        if(t.equals(issuedBooks[i],book)){
          b=issuedBooks[i];
          System.out.printf("Name: %s (%d) Author: %s\n",b.name,b.id,b.author);
        }
      }
      return;
    }
    System.out.println("Book Not Found!");
  }
  int searchAvailBook(Book book){
    for(int i=0;i<=availTop;i++){
      if(t.equals(availBooks[i],book)){
        return i;
      }
    }
    return -1;
  }
  int searchIssuedBook(Book book){
    for(int i=0;i<=issuedTop;i++){
      if(t.equals(issuedBooks[i],book)){
        return i;
      }
    }
    return -1;
  }
  void clean(){
    //Cleaning up Available Books
    int i,p=0;
    for(i=0;i<=availTop;i++){
      if(availBooks[i].name==null && availBooks[i].author==null){
        p++;
        break;
      }
    }
    while(i<=availTop-p){
      if(availBooks[i+p].name!=null||availBooks[i+p].author!=null){
        availBooks[i]=availBooks[i+p];
        i++;
      }
      else {
        availBooks[i]=null;
        p++;
      }
    }
    while(i<=availTop){
      availBooks[i]=null;
      i++;
    }
    availTop=availTop-p;
    //Cleaning up Issued Books
    p=0;
    for(i=0;i<=issuedTop;i++){
      if(issuedBooks[i].name==null && issuedBooks[i].author==null){
        p++;
        break;
      }
    }
    while(i<=issuedTop-p){
      if(issuedBooks[i].name!=null||issuedBooks[i].author!=null){
        issuedBooks[i]=issuedBooks[i+p];
        i++;
      }
      else {
        issuedBooks[i]=null;
        p++;
      }
    }
    while(i<=issuedTop){
      issuedBooks[i]=null;
      i++;
    }
    issuedTop=issuedTop-p;
    System.gc();
  }
}
class Tools {
  Scanner sc=new Scanner(System.in);
  boolean equals(Book b1,Book b2) {
    if(b1==null || b2==null){
      return false;
    }
    if(b1.name==null){
      return false;
    }
    if(b1.name.equalsIgnoreCase(b2.name) || b2.name==null){
      if(b1.author==null){
        return false;
      }
      if(b1.author.equalsIgnoreCase(b2.author) || b2.author==null){
        return true;
      }
    }
    return false;
  }
  String inputString(String str){
    System.out.printf("Enter %s name: ",str);
    String name=sc.nextLine();
    name=name.trim();
    return name;
  }
}

class Main {
  public static void main(String[] args){
    System.out.println("1. Add Book");
    System.out.println("2. Remove Book");
    System.out.println("3. Issue Book");
    System.out.println("4. Return Book");
    System.out.println("5. Search Book");
    System.out.println("6. Show Available Books");
    System.out.println("7. Show Issued Books");
    System.out.println("8. Exit");
    Library l=new Library();
    Tools t=new Tools();
    Scanner sc=new Scanner(System.in);
    int choice;
    while(true){
      Book book;
      System.out.println("No. of Available Books: "+(l.availTop+1));
      System.out.println("No. of Issued Books: "+(l.issuedTop+1));
      System.out.print("Enter your choice: ");
      choice=sc.nextInt();
      sc.nextLine();
      switch(choice){
        case 1: book=new Book(t.inputString("Book"),t.inputString("Author"));
                l.addBook(book);
                break;
        case 2: book=new Book(t.inputString("Book"),t.inputString("Author"));
                l.removeBook(book);
                break;
        case 3: book=new Book(t.inputString("Book"),t.inputString("Author"));
                l.issueBook(book);
                break;
        case 4: book=new Book(t.inputString("Book"),t.inputString("Author"));
                l.returnBook(book);
                break;
        case 5: book=new Book(t.inputString("Book"),t.inputString("Author"));
                l.searchBook(book);
                break;
        case 6: l.showAvailableBooks();
                break;
        case 7: l.showIssuedBooks();
                break;
        case 8: System.exit(0);
        default: System.out.println("Wrong Choice!");
      }
      l.clean();
    }
  }
}
