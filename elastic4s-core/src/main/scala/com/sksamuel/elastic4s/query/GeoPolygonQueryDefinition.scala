package com.sksamuel.elastic4s.query

import com.sksamuel.elastic4s.QueryDefinition
import org.elasticsearch.common.geo.GeoPoint
import org.elasticsearch.index.query.{GeoPolygonQueryBuilder, GeoValidationMethod, QueryBuilders}

import scala.collection.JavaConverters._

case class GeoPolygonQueryDefinition(field: String,
                                     points: Seq[GeoPoint],
                                     ignoreUnmapped: Option[Boolean] = None,
                                     validationMethod: Option[GeoValidationMethod] = None,
                                     boost: Option[Float] = None,
                                     queryName: Option[String] = None)
  extends QueryDefinition {

  def builder: GeoPolygonQueryBuilder = {
    val builder = QueryBuilders.geoPolygonQuery(field, points.asJava)
    boost.foreach(builder.boost)
    queryName.foreach(builder.queryName)
    validationMethod.foreach(builder.setValidationMethod)
    ignoreUnmapped.foreach(builder.ignoreUnmapped)
    builder
  }

  def boost(boost: Float) = copy(boost = Option(boost))
  def queryName(queryName: String) = copy(queryName = Some(queryName))
}