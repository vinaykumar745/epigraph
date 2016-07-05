/* Created by yegor on 6/17/16. */

package com.sumologic.epigraph.schema.compiler

import java.io.File

import org.jetbrains.annotations.NotNull
import pprint.{Config, PPrint, PPrinter}

import scala.collection.GenTraversableOnce
import scala.collection.JavaConversions._

object CPrettyPrinters {

  implicit object CErrorPrinter extends PPrinter[CError] {

    override def render0(t: CError, c: Config): Iterator[String] = Iterator(
      t.filename, ":", t.position.line.toString, ":", t.position.column.toString, " ", intellijLink(t),
      "\nError: ", t.message, "\n" // TODO skip :line:colon, line text, and ^ if NA
    ) ++ t.position.lineText.iterator ++ Iterator("\n", " " * (t.position.column - 1), "^")

    private def intellijLink(t: CError): String = { // relies on '.' already rendered (as part of canonical path
      "(" + new File(t.filename).getName + ":" + t.position.line + ")"
    }

  }

  implicit val CErrorPrint: PPrint[CError] = PPrint(CErrorPrinter)


  implicit object CSchemaFilePrinter extends PPrinter[CSchemaFile] {

    override def render0(t: CSchemaFile, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.filename, c, (c: Config) => Iterator( // TODO PPrint[CNamespace]
          implicitly[PPrint[CNamespace]].pprinter.render(t.namespace, c),
          compound("imports", t.imports, c), // TODO PPrint[CImport]
          compound("typedefs", t.types, c),
          compound("supplements", t.supplements, c) // TODO PPrint[CSupplement]
        )
      )
    }
  }

  implicit val CSchemaFilePrint: PPrint[CSchemaFile] = PPrint(CSchemaFilePrinter)


  implicit object CTypeRefPrinter extends PPrinter[CTypeRef] {

    override def render0(t: CTypeRef, c: Config): Iterator[String] =
      if (t.isResolved) {
        Iterator(t.name.name)
      } else {
        Iterator("«", t.name.name, "»")
      }

  }

  implicit val CTypeRefPrint: PPrint[CTypeRef] = PPrint(CTypeRefPrinter)


  implicit object CTypeNamePrinter extends PPrinter[CTypeName] {

    override def render0(t: CTypeName, c: Config): Iterator[String] = Iterator(t.name)

  }

  implicit val CTypeNamePrint: PPrint[CTypeName] = PPrint(CTypeNamePrinter)


  implicit object CTypePrinter extends PPrinter[CType] {

    override def render0(t: CType, c: Config): Iterator[String] = {
      t match {
        case td: CTypeDef => CTypeDefPrinter.render(td, c)
        case amt: CAnonMapType => CAnonMapTypePrinter.render(amt, c)
        case alt: CAnonListType => CAnonListTypePrinter.render(alt, c)
        case _ => Iterator("UNKNOWN ", t.name.name)
      }
    }

  }

  implicit val CTypePrint: PPrint[CType] = PPrint(CTypePrinter)

  implicit def cTypePrint[T <: CType]: PPrint[T] = CTypePrint.asInstanceOf[PPrint[T]]


  implicit object CTypeDefPrinter extends PPrinter[CTypeDef] {

    override def render0(td: CTypeDef, c: Config): Iterator[String] = {
      td match {
        case vt: CVarTypeDef => CVarTypePrinter.render(vt, c)
        case rt: CRecordTypeDef => CRecordTypePrinter.render(rt, c)
        case mt: CMapTypeDef => CMapTypePrinter.render(mt, c)
        case lt: CListTypeDef => CListTypePrinter.render(lt, c)
        case et: CEnumTypeDef => CEnumTypePrinter.render(et, c)
        case pt: CPrimitiveTypeDef => CPrimitiveTypePrinter.render(pt, c)
        case _ => Iterator("UNKNOWN ", td.name.name)
      }
    }

    def typeDefParts(@NotNull t: CTypeDef, c: Config): Iterator[Iterator[String]] = Iterator(
      compound("declaredSupertypes", t.declaredSupertypeRefs, c),
      compound("injectedSupertypes", t.injectedSupertypes, (x: CTypeDef) => x.name, c),
      compound("parents", t.parents, (x: CTypeDef) => x.name, c),
      compound("linearizedParents", t.linearizedParents, (x: CTypeDef) => x.name, c),
      compound("linearization", t.linearized, (x: CTypeDef) => x.name, c),
      compound("effectiveSupertypes", t.supertypes, (x: CTypeDef) => x.name, c),
      compound("supplementedSubtypes", t.declaredSupplementees, c)
    )

  }

  //implicit val CTypeDefPrint: PPrint[CTypeDef] = PPrint(CTypeDefPrinter)


  private def compound[A: PPrint](name: String, source: GenTraversableOnce[A], c: Config): Iterator[String] =
    compound(name, source, identity[A], c)

  private def compound[A, B: PPrint](
      name: String,
      source: GenTraversableOnce[A],
      trans: A => B,
      c: Config
  ): Iterator[String] = if (source == null) {
    pprint.Internals.handleChunks(name, c, (c: Config) => Iterator(Iterator("?")))
  } else {
    pprint.Internals.handleChunks(
      name, c, (c: Config) => source.toIterator.map { a: A => implicitly[PPrint[B]].pprinter.render(trans(a), c) }
    )
  }


  implicit object CVarTypePrinter extends PPrinter[CVarTypeDef] {

    override def render0(@NotNull t: CVarTypeDef, c: Config): Iterator[String] = {
      def body = (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
        compound("declaredTags", t.declaredTags, c),
//        pprint.Internals.handleChunks(
//          "alltags", c, (c: Config) => t.allTags.toIterator.map { case (n, vts) => (n, vts.map(_.name)) }.map(
//            implicitly[PPrint[(String, Seq[CTypeFqn])]].render(_, c)
//          )
//        ),
        compound("effectiveTags", t.effectiveTagsMap, (e: (String, CTag)) => e._2, c)
      )
      pprint.Internals.handleChunks("var " + t.name.name, c, body)
    }

  }

  implicit val CVarTypePrint: PPrint[CVarTypeDef] = PPrint(CVarTypePrinter)

  implicit object CTagPrinter extends PPrinter[CTag] {

    override def render0(t: CTag, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.name + ": " + CTypeRefPrint.render(t.typeRef, c).mkString, c, (c: Config) => Iterator(
          // TODO tag attributes etc.
        )
      )
    }

  }

  implicit val CTagPrint: PPrint[CTag] = PPrint(CTagPrinter)


  implicit object CRecordTypePrinter extends PPrinter[CRecordTypeDef] {

    override def render0(@NotNull t: CRecordTypeDef, c: Config): Iterator[String] = {
      def body = (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
        compound("declaredFields", t.declaredFields, c)
      )
      pprint.Internals.handleChunks("record " + t.name.name, c, body)
    }

  }

  implicit val CRecordTypePrint: PPrint[CRecordTypeDef] = PPrint(CRecordTypePrinter)

  implicit object CFieldPrinter extends PPrinter[CField] {

    override def render0(t: CField, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.name + ": " + CTypeRefPrinter.render(t.typeRef, c).mkString, c, (c: Config) => Iterator(
          // TODO field attributes etc.
        )
      )
    }

  }

  implicit val CFieldPrint: PPrint[CField] = PPrint(CFieldPrinter)


  implicit object CAnonMapTypePrinter extends PPrinter[CAnonMapType] {

    override def render0(@NotNull t: CAnonMapType, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.name.name, c, (c: Config) => Iterator(
          Iterator("keyType: ") ++ CTypeRefPrinter.render(t.keyTypeRef, c),
          Iterator("valueType: ") ++ CTypeRefPrinter.render(t.valueTypeRef, c)
        )
      )
    }
  }

  implicit val CAnonMapTypePrint: PPrint[CAnonMapType] = PPrint(CAnonMapTypePrinter)

  implicit object CMapTypePrinter extends PPrinter[CMapTypeDef] {

    override def render0(@NotNull t: CMapTypeDef, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        "map " + CTypeNamePrinter.render(t.name, c).mkString, c,
        (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
          Iterator("keyType: ") ++ CTypeRefPrinter.render(t.keyTypeRef, c),
          Iterator("valueType: ") ++ CTypeRefPrinter.render(t.valueTypeRef, c)
        )
      )
    }
  }

  implicit val CMapTypePrint: PPrint[CMapTypeDef] = PPrint(CMapTypePrinter)


  implicit object CAnonListTypePrinter extends PPrinter[CAnonListType] {

    override def render0(@NotNull t: CAnonListType, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.name.name, c, (c: Config) => Iterator(
          Iterator("elementType: ") ++ CTypeRefPrinter.render(t.elementTypeRef, c)
        )
      )
    }
  }

  implicit val CAnonListTypePrint: PPrint[CAnonListType] = PPrint(CAnonListTypePrinter)


  implicit object CListTypePrinter extends PPrinter[CListTypeDef] {

    override def render0(@NotNull t: CListTypeDef, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        "list " + t.name.name, c, (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
          Iterator("valueType", t.elementTypeRef.name.name)
        )
      )
    }
  }

  implicit val CListTypePrint: PPrint[CListTypeDef] = PPrint(CListTypePrinter)


  implicit object CEnumTypePrinter extends PPrinter[CEnumTypeDef] {

    override def render0(@NotNull t: CEnumTypeDef, c: Config): Iterator[String] = {
      def body = (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
        compound("values", t.values, c)
      )
      pprint.Internals.handleChunks("enum " + t.name.name, c, body)
    }

  }

  implicit val CEnumTypePrint: PPrint[CEnumTypeDef] = PPrint(CEnumTypePrinter)

  implicit object CEnumValuePrinter extends PPrinter[CEnumValue] {

    override def render0(t: CEnumValue, c: Config): Iterator[String] = {
      Iterator(t.name)
    }

  }

  implicit val CEnumValuePrint: PPrint[CEnumValue] = PPrint(CEnumValuePrinter)


  implicit object CPrimitiveTypePrinter extends PPrinter[CPrimitiveTypeDef] {

    override def render0(@NotNull t: CPrimitiveTypeDef, c: Config): Iterator[String] = {
      pprint.Internals.handleChunks(
        t.kind.keyword + " " + t.name.name, c, (c: Config) => CTypeDefPrinter.typeDefParts(t, c) ++ Iterator(
          // TODO enum attributes
        )
      )
    }
  }

  implicit val CPrimitiveTypePrint: PPrint[CPrimitiveTypeDef] = PPrint(CPrimitiveTypePrinter)

}
