package filters

import javax.inject.Inject

import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter

class Filters @Inject() (gzipFilter: GzipFilter) extends HttpFilters {
    def filters = Seq(gzipFilter) // append any other filters
}

//object Node{
//    def setup(n:Int):Node = {
//
//        var node:Node = null
//
//        for (i <- 1 to n reverse ){
//            node = new Node(i, node)
//        }
//        node
//    }
//
//    def reverse(pre: Node, next: Node):Node = {
//        if (next == null){
//            return pre
//        }
//        val node = reverse(next, next.n)
//        next.n = pre
//        node
//    }
//}
//
//class Node(value:Int, next:Node){
//    val v = value
//    var n = next
//
//    def printLine(): Unit = {
//        println(v)
//        if (n != null) n.printLine
//    }
//
//}
