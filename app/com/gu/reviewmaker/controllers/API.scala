package controllers

import java.util.UUID
import com.gu.atom.data.{IDConflictError, PreviewDataStore}
import com.gu.contentatom.thrift.atom.review.ReviewAtom
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.circe.Circe
import play.api.mvc._
import com.gu.contentapi.json.CirceEncoders._
import com.gu.contentapi.circe.CirceScroogeMacros._
import com.gu.contentatom.thrift._
import io.circe.syntax._
import io.circe.generic.auto._
import scala.concurrent.ExecutionContext

case class ErrorResponse(error: String)

class API(previewDataStore: PreviewDataStore) extends Controller with Circe {

  def createReview = Action(atomBodyParser) { implicit req =>
    /* Note: If we want control over the response on error (play behaviour defaults to 500) then we can handle
     * the decoding ourselves as opposed to using the Circe body parser provided. */
    val atom = req.body

    previewDataStore.createAtom(atom).fold(
      {
        case IDConflictError => Conflict(s"${atom.id} already exists")
        case _ => InternalServerError(ErrorResponse("Unknown error").asJson)
      },
      _ => {
        // TODO - publish atom to CAPI
        Created(atom.asJson).withHeaders("Location" -> s"/atom/${atom.id}")
      }
    )
  }

  def getReviewAtom(id: String) = Action {
    previewDataStore.getAtom(id) match {
      case Some(atom) => Ok(atom.asJson)
      case None => NotFound(ErrorResponse(s"no atom with id $id found").asJson)
    }
  }

  def listAtoms = Action {
      previewDataStore.listAtoms.fold(
        error => InternalServerError(ErrorResponse(error.msg).asJson),
        atoms => Ok(atoms.toSeq.asJson)
      )
  }


  private def atomBodyParser(implicit ec: ExecutionContext): BodyParser[Atom] =
    circe.json[ReviewAtom] map { reviewAtom =>
      Atom(
        id = UUID.randomUUID.toString,
        atomType = AtomType.Review,
        defaultHtml = "<div></div>",
        data = AtomData.Review(reviewAtom),
        contentChangeDetails = ContentChangeDetails(
          None, None, None, 1L
        )
      )
    }




}
