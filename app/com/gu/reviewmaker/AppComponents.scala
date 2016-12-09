import com.gu.contentatom.thrift.AtomData
import com.gu.contentatom.thrift.atom.review.ReviewAtom
import com.gu.reviewmaker.AWSConfig
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import controllers._
import router.Routes
import play.api.i18n.I18nComponents
import com.gu.scanamo.scrooge.ScroogeDynamoFormat._
import com.gu.atom.data._
import play.api.libs.concurrent.Execution.Implicits._

object Stores {

  val previewDataStore = new PreviewDynamoDataStore[ReviewAtom](AWSConfig.dynamoDB, AWSConfig.draftTableName) {
      def fromAtomData = { case AtomData.Review(data) => data }
      def toAtomData(data: ReviewAtom) = AtomData.Review(data)
  }

  val publishedDataStore = new PublishedDynamoDataStore[ReviewAtom](AWSConfig.dynamoDB, AWSConfig.publishedTableName) {
    def fromAtomData = { case AtomData.Review(data) => data }
    def toAtomData(data: ReviewAtom) = AtomData.Review(data)
  }
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with I18nComponents {


  val healthcheckController = new Healthcheck
  val applicationController = new Application
  val apiController = new API(Stores.previewDataStore)


  val assets = new Assets(httpErrorHandler)
  val router: Router = new Routes(httpErrorHandler, healthcheckController, applicationController, apiController, assets)
}