package controllers

import play.api.mvc.{Action, Controller}
import com.gu.reviewmaker.views

class Application extends Controller {

  def index = Action {
    Ok(views.html.app("Review Maker"))
  }

}
