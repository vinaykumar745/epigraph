/*
 * Copyright 2016 Sumo Logic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* Created by yegor on 6/6/16. */

package ws.epigraph.xp.data.mutable

import ws.epigraph.names.QualifiedNamespaceName
import ws.epigraph.xp.data._
import ws.epigraph.xp.data.immutable._
import ws.epigraph.xp.types._

object MutableRecordTest {

  val ns: QualifiedNamespaceName = QualifiedNamespaceName(None, "example")


  trait FooId extends IntegerDatum[FooId]


  trait ImmFooId extends FooId with ImmIntegerDatum[FooId]


  trait MutFooId extends FooId with MutIntegerDatum[FooId]


  object FooId extends IntegerType[FooId](ns \ "FooId") {

    override def createImmutable(native: Int): ImmFooId = new ImmFooIdImpl(native)


    private class ImmFooIdImpl(native: Int) extends ImmIntegerDatumImpl(native) with ImmFooId


    override def createMutable(native: Int): MutFooId = new MutFooIdImpl(native)


    private class MutFooIdImpl(native: Int) extends MutIntegerDatumImpl(native) with MutFooId


  }


  trait BarId extends FooId with IntegerDatum[BarId]


  trait ImmBarId extends ImmFooId with BarId with ImmIntegerDatum[BarId]


  trait MutBarId extends MutFooId with BarId with MutIntegerDatum[BarId]


  object BarId extends IntegerType[BarId](ns \ "BarId", Seq(FooId)) {

    override def createImmutable(native: Int): ImmBarId = new ImmBarIdImpl(native)


    private class ImmBarIdImpl(native: Int) extends ImmIntegerDatumImpl(native) with ImmBarId


    override def createMutable(native: Int): MutBarId = new MutBarIdImpl(native)


    private class MutBarIdImpl(native: Int) extends MutIntegerDatumImpl(native) with MutBarId


  }


  trait FooRecord extends RecordDatum[FooRecord]

//  trait ImmFooRecord extends FooRecord with ImmRecordDatum[FooRecord]

  trait MutFooRecord extends FooRecord with MutRecordDatum[FooRecord]


  object FooRecord extends RecordType[FooRecord](ns \ "FooRecord") {

    val _id: DatumField[FooId] = field("id", FooId)

    override def declaredFields: ws.epigraph.xp.data.mutable.MutableRecordTest.FooRecord.DeclaredFields =
      DeclaredFields(_id)

    override def createMutable: MutFooRecord = new MutFooRecord {
      override def dataType: FooRecord.type = FooRecord
    }
  }


  trait BarRecord extends FooRecord with RecordDatum[BarRecord]

  trait MutBarRecord extends MutFooRecord with BarRecord with MutRecordDatum[BarRecord]


  object BarRecord extends RecordType[BarRecord](ns \ "BarRecord") {

    override def declaredFields: ws.epigraph.xp.data.mutable.MutableRecordTest.BarRecord.DeclaredFields =
      DeclaredFields()

    override def createMutable: MutBarRecord = new MutBarRecord {
      override def dataType: BarRecord.type = BarRecord
    }
  }


  def main(args: Array[String]) {
    implicit val PPConfig = pprint.Config(
      width = 120, colors = pprint.Colors(fansi.Color.Green, fansi.Color.LightBlue)
    )

    val fr = BarRecord.createMutable
    val fid = FooId.createMutable(1)
    fr.setData(FooRecord._id, fid)
    val frid = fr.getData(FooRecord._id)
    println(fr)
    println(frid)
    fr.setData(FooRecord._id, BarId.createMutable(2))
    println(fr.getData(FooRecord._id))

    println("--------------")
    import DataPrettyPrinters._
    pprint.pprintln(fr)
    pprint.pprintln(fr: Var[BarRecord])
    pprint.pprintln(fr: MutFooRecord)
    pprint.pprintln(fr: Var[FooRecord])
  }

}
