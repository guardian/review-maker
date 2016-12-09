package com.gu.reviewmaker

import com.amazonaws.auth.{AWSCredentialsProviderChain, InstanceProfileCredentialsProvider}
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.kinesis.AmazonKinesisClient

object AWSConfig {

  val region = Region.getRegion(Regions.EU_WEST_1) // TODO: make configurable

  val credProvider = new AWSCredentialsProviderChain(
    new ProfileCredentialsProvider("composer"),
    InstanceProfileCredentialsProvider.getInstance()
  )

  val dynamoDB = region.createClient(classOf[AmazonDynamoDBClient], credProvider, null)

  val kinesisClient = region.createClient(classOf[AmazonKinesisClient], credProvider, null)

  val stage = "CODE"

  val draftTableName = "draft-review-atom-maker-CODE" // TODO make configurable
  val publishedTableName = "published-review-atom-maker-CODE" // TODO make configurable

  val liveIndexStreamName = ""
  val previewIndexStreamName = ""

  val draftReindexStreamName = ""
  val publishedReindexStreamName = ""

}
