package v_builders

import syntax.forWhileLoops.forLoop
import util.TODO
import util.doc39
import v_builders.data.getProducts
import v_builders.htmlLibrary.*

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) %2 == 0) "#dce4ff" else "#eff2ff"

fun todoTask39(): Nothing = TODO(
    """
        Task 39.
        1) Fill the table with the proper values from products.
        2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
        Pass a color as an argument to functions 'tr', 'td'.
        You can run the 'Html Demo' configuration in IntelliJ IDEA to see the rendered table.
    """,
    documentation = doc39()
)

fun renderProductTable(): String {
    return html {
        table {
            tr( init= {
                td (init = {
                    text("Product")
                }, align = "center")
                td (init = {
                    text("Price")
                }, align = "center")
                td (init = {
                    text("Popularity")
                }, align = "center")
            }, color = getTitleColor())

            val products = getProducts()
            var row = 0

            for ( p in  products) {
                tr {
                    td( init = {
                        text(p.description)
                    }, color = getCellColor(row,0), align = "left")
                    td( init = {
                        text(p.price)
                    }, color = getCellColor(row,1), align = "right")
                    td( init = {
                        text(p.popularity)
                    }, color = getCellColor(row,2), align = "center")
                }
                row++
            }
        }
    }.toString()
}
