class Book{
   String  title
}
def aBook = new Book()
aBook.setTitle("Groovy in action")
assert aBook.getTitle() =='Groovy in action'
println  aBook.getTitle()