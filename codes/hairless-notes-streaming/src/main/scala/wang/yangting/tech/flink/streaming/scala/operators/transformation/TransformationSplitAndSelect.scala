package wang.yangting.tech.flink.streaming.scala.operators.transformation

import org.apache.flink.streaming.api.scala.{DataStream, SplitStream, StreamExecutionEnvironment}

/**
  * @author yx.zhang
  */
object TransformationSplitAndSelect {
  def main(args: Array[String]): Unit = {
      val env  = StreamExecutionEnvironment.getExecutionEnvironment
      import org.apache.flink.api.scala._
      val dataStream1: DataStream[(String,Int)] = env.fromElements(("a",3),("d",4),("c",2),("c",5),("a",5))
      val splitStream:SplitStream[(String,Int)] = dataStream1
        .split(t => if (t._2 % 2 == 0) Seq("even") else Seq("old"))
      val evenStream : DataStream[(String,Int)] = splitStream.select("even")
      val oddStream : DataStream[(String,Int)] = splitStream.select("odd")
      evenStream.print()
      env.execute()

  }
}
