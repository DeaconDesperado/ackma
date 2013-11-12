import javax.naming.directory.{InitialDirContext, Attribute}
import javax.naming.NamingException
import scala.collection.JavaConversions._

object L {
    def main(args: Array[String]) {
        println('ip, lookupIp(args.head))
    }

    def lookupIp(host: String): List[String] = {
        val attributes = try {
            new InitialDirContext getAttributes ("dns:/%s" format host)
        } catch {
            case _: NamingException => return Nil
        }
        val list = {
            val attributeEnumeration = attributes.getAll
            var list = List[Attribute]()
            while (attributeEnumeration.hasMore)
                list = attributeEnumeration.next :: list
            attributeEnumeration.close
            list.reverse
        }
        list map (x => x.getID -> x.get.toString) flatMap {
            case ("A", x) => List(x)
            case ("CNAME", x) => lookupIp(x)
            case (_, x) => Nil
        }
    }
}
