import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import controllers._
import play.api.i18n.I18nComponents
import router.Routes
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits._

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with I18nComponents {


  val healthcheckController = new Healthcheck
  val applicationController = new Application

  val assets = new Assets(httpErrorHandler)
  val router: Router = new Routes(httpErrorHandler, healthcheckController, applicationController, assets)
}