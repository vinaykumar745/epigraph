// This is a generated file. Not intended for manual editing.
package io.epigraph.idl.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.epigraph.idl.lexer.IdlElementTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class IdlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == I_CUSTOM_PARAM) {
      r = customParam(b, 0);
    }
    else if (t == I_DATA_ENUM) {
      r = dataEnum(b, 0);
    }
    else if (t == I_DATA_LIST) {
      r = dataList(b, 0);
    }
    else if (t == I_DATA_MAP) {
      r = dataMap(b, 0);
    }
    else if (t == I_DATA_MAP_ENTRY) {
      r = dataMapEntry(b, 0);
    }
    else if (t == I_DATA_NULL) {
      r = dataNull(b, 0);
    }
    else if (t == I_DATA_PRIMITIVE) {
      r = dataPrimitive(b, 0);
    }
    else if (t == I_DATA_RECORD) {
      r = dataRecord(b, 0);
    }
    else if (t == I_DATA_RECORD_ENTRY) {
      r = dataRecordEntry(b, 0);
    }
    else if (t == I_DATA_VALUE) {
      r = dataValue(b, 0);
    }
    else if (t == I_DATA_VAR) {
      r = dataVar(b, 0);
    }
    else if (t == I_DATA_VAR_ENTRY) {
      r = dataVarEntry(b, 0);
    }
    else if (t == I_FQN) {
      r = fqn(b, 0);
    }
    else if (t == I_FQN_SEGMENT) {
      r = fqnSegment(b, 0);
    }
    else if (t == I_FQN_TYPE_REF) {
      r = fqnTypeRef(b, 0);
    }
    else if (t == I_IMPORT_STATEMENT) {
      r = importStatement(b, 0);
    }
    else if (t == I_IMPORTS) {
      r = imports(b, 0);
    }
    else if (t == I_NAMESPACE_DECL) {
      r = namespaceDecl(b, 0);
    }
    else if (t == I_OP_INPUT_DEFAULT_VALUE) {
      r = opInputDefaultValue(b, 0);
    }
    else if (t == I_OP_INPUT_FIELD_PROJECTION) {
      r = opInputFieldProjection(b, 0);
    }
    else if (t == I_OP_INPUT_FIELD_PROJECTION_BODY) {
      r = opInputFieldProjectionBody(b, 0);
    }
    else if (t == I_OP_INPUT_FIELD_PROJECTION_BODY_PART) {
      r = opInputFieldProjectionBodyPart(b, 0);
    }
    else if (t == I_OP_INPUT_KEY_PROJECTION) {
      r = opInputKeyProjection(b, 0);
    }
    else if (t == I_OP_INPUT_KEY_PROJECTION_PART) {
      r = opInputKeyProjectionPart(b, 0);
    }
    else if (t == I_OP_INPUT_LIST_MODEL_PROJECTION) {
      r = opInputListModelProjection(b, 0);
    }
    else if (t == I_OP_INPUT_MAP_MODEL_PROJECTION) {
      r = opInputMapModelProjection(b, 0);
    }
    else if (t == I_OP_INPUT_MODEL_PROJECTION) {
      r = opInputModelProjection(b, 0);
    }
    else if (t == I_OP_INPUT_MODEL_PROJECTION_BODY) {
      r = opInputModelProjectionBody(b, 0);
    }
    else if (t == I_OP_INPUT_MODEL_PROJECTION_BODY_PART) {
      r = opInputModelProjectionBodyPart(b, 0);
    }
    else if (t == I_OP_INPUT_MULTI_TAG_PROJECTION) {
      r = opInputMultiTagProjection(b, 0);
    }
    else if (t == I_OP_INPUT_MULTI_TAG_PROJECTION_ITEM) {
      r = opInputMultiTagProjectionItem(b, 0);
    }
    else if (t == I_OP_INPUT_RECORD_MODEL_PROJECTION) {
      r = opInputRecordModelProjection(b, 0);
    }
    else if (t == I_OP_INPUT_REQUIRED) {
      r = opInputRequired(b, 0);
    }
    else if (t == I_OP_INPUT_SINGLE_TAG_PROJECTION) {
      r = opInputSingleTagProjection(b, 0);
    }
    else if (t == I_OP_INPUT_VAR_MULTI_TAIL) {
      r = opInputVarMultiTail(b, 0);
    }
    else if (t == I_OP_INPUT_VAR_MULTI_TAIL_ITEM) {
      r = opInputVarMultiTailItem(b, 0);
    }
    else if (t == I_OP_INPUT_VAR_POLYMORPHIC_TAIL) {
      r = opInputVarPolymorphicTail(b, 0);
    }
    else if (t == I_OP_INPUT_VAR_PROJECTION) {
      r = opInputVarProjection(b, 0);
    }
    else if (t == I_OP_INPUT_VAR_SINGLE_TAIL) {
      r = opInputVarSingleTail(b, 0);
    }
    else if (t == I_OP_OUTPUT_FIELD_PROJECTION) {
      r = opOutputFieldProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_FIELD_PROJECTION_BODY) {
      r = opOutputFieldProjectionBody(b, 0);
    }
    else if (t == I_OP_OUTPUT_FIELD_PROJECTION_BODY_PART) {
      r = opOutputFieldProjectionBodyPart(b, 0);
    }
    else if (t == I_OP_OUTPUT_KEY_PROJECTION) {
      r = opOutputKeyProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_KEY_PROJECTION_PART) {
      r = opOutputKeyProjectionPart(b, 0);
    }
    else if (t == I_OP_OUTPUT_LIST_MODEL_PROJECTION) {
      r = opOutputListModelProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_MAP_MODEL_PROJECTION) {
      r = opOutputMapModelProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_MODEL_PROJECTION) {
      r = opOutputModelProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_MODEL_PROJECTION_BODY) {
      r = opOutputModelProjectionBody(b, 0);
    }
    else if (t == I_OP_OUTPUT_MODEL_PROJECTION_BODY_PART) {
      r = opOutputModelProjectionBodyPart(b, 0);
    }
    else if (t == I_OP_OUTPUT_MULTI_TAG_PROJECTION) {
      r = opOutputMultiTagProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_MULTI_TAG_PROJECTION_ITEM) {
      r = opOutputMultiTagProjectionItem(b, 0);
    }
    else if (t == I_OP_OUTPUT_RECORD_MODEL_PROJECTION) {
      r = opOutputRecordModelProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_SINGLE_TAG_PROJECTION) {
      r = opOutputSingleTagProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_VAR_MULTI_TAIL) {
      r = opOutputVarMultiTail(b, 0);
    }
    else if (t == I_OP_OUTPUT_VAR_MULTI_TAIL_ITEM) {
      r = opOutputVarMultiTailItem(b, 0);
    }
    else if (t == I_OP_OUTPUT_VAR_POLYMORPHIC_TAIL) {
      r = opOutputVarPolymorphicTail(b, 0);
    }
    else if (t == I_OP_OUTPUT_VAR_PROJECTION) {
      r = opOutputVarProjection(b, 0);
    }
    else if (t == I_OP_OUTPUT_VAR_SINGLE_TAIL) {
      r = opOutputVarSingleTail(b, 0);
    }
    else if (t == I_OP_PARAM_PROJECTION) {
      r = opParamProjection(b, 0);
    }
    else if (t == I_OP_PARAMETERS) {
      r = opParameters(b, 0);
    }
    else if (t == I_QID) {
      r = qid(b, 0);
    }
    else if (t == I_VAR_VALUE) {
      r = varValue(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(I_OP_OUTPUT_LIST_MODEL_PROJECTION, I_OP_OUTPUT_MAP_MODEL_PROJECTION, I_OP_OUTPUT_MODEL_PROJECTION, I_OP_OUTPUT_RECORD_MODEL_PROJECTION),
    create_token_set_(I_OP_INPUT_LIST_MODEL_PROJECTION, I_OP_INPUT_MAP_MODEL_PROJECTION, I_OP_INPUT_MODEL_PROJECTION, I_OP_INPUT_RECORD_MODEL_PROJECTION),
    create_token_set_(I_DATA_ENUM, I_DATA_LIST, I_DATA_MAP, I_DATA_NULL,
      I_DATA_PRIMITIVE, I_DATA_RECORD, I_VAR_VALUE),
  };

  /* ********************************************************** */
  // qid '=' dataValue
  public static boolean customParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "customParam")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_CUSTOM_PARAM, "<custom attribute>");
    r = qid(b, l + 1);
    r = r && consumeToken(b, I_EQ);
    p = r; // pin = 2
    r = r && dataValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // qid
  public static boolean dataEnum(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataEnum")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    exit_section_(b, m, I_DATA_ENUM, r);
    return r;
  }

  /* ********************************************************** */
  // dataTypeSpec? '[' (dataValue ','?)* ']'
  public static boolean dataList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataList")) return false;
    if (!nextTokenIs(b, "<data list>", I_BRACKET_LEFT, I_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_LIST, "<data list>");
    r = dataList_0(b, l + 1);
    r = r && consumeToken(b, I_BRACKET_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, dataList_2(b, l + 1));
    r = p && consumeToken(b, I_BRACKET_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // dataTypeSpec?
  private static boolean dataList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataList_0")) return false;
    dataTypeSpec(b, l + 1);
    return true;
  }

  // (dataValue ','?)*
  private static boolean dataList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataList_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dataList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dataList_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // dataValue ','?
  private static boolean dataList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataList_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dataValue(b, l + 1);
    r = r && dataList_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean dataList_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataList_2_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // dataTypeSpec? '(' dataMapEntry* ')'
  public static boolean dataMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataMap")) return false;
    if (!nextTokenIs(b, "<data map>", I_PAREN_LEFT, I_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_MAP, "<data map>");
    r = dataMap_0(b, l + 1);
    r = r && consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, dataMap_2(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // dataTypeSpec?
  private static boolean dataMap_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataMap_0")) return false;
    dataTypeSpec(b, l + 1);
    return true;
  }

  // dataMapEntry*
  private static boolean dataMap_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataMap_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dataMapEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dataMap_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // varValue ':' dataValue ','?
  public static boolean dataMapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataMapEntry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_MAP_ENTRY, "<data map entry>");
    r = varValue(b, l + 1);
    r = r && consumeToken(b, I_COLON);
    p = r; // pin = 2
    r = r && report_error_(b, dataValue(b, l + 1));
    r = p && dataMapEntry_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, dataValueRecover_parser_);
    return r || p;
  }

  // ','?
  private static boolean dataMapEntry_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataMapEntry_3")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // dataSpeccedNull | dataUnspeccedNull
  public static boolean dataNull(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataNull")) return false;
    if (!nextTokenIs(b, "<data null>", I_NULL, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_NULL, "<data null>");
    r = dataSpeccedNull(b, l + 1);
    if (!r) r = dataUnspeccedNull(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (dataTypeSpec '@')? (string | number | boolean)
  public static boolean dataPrimitive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataPrimitive")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_PRIMITIVE, "<data primitive>");
    r = dataPrimitive_0(b, l + 1);
    r = r && dataPrimitive_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (dataTypeSpec '@')?
  private static boolean dataPrimitive_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataPrimitive_0")) return false;
    dataPrimitive_0_0(b, l + 1);
    return true;
  }

  // dataTypeSpec '@'
  private static boolean dataPrimitive_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataPrimitive_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dataTypeSpec(b, l + 1);
    r = r && consumeToken(b, I_AT);
    exit_section_(b, m, null, r);
    return r;
  }

  // string | number | boolean
  private static boolean dataPrimitive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataPrimitive_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_STRING);
    if (!r) r = consumeToken(b, I_NUMBER);
    if (!r) r = consumeToken(b, I_BOOLEAN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // dataTypeSpec? '{' dataRecordEntry* '}'
  public static boolean dataRecord(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataRecord")) return false;
    if (!nextTokenIs(b, "<data record>", I_CURLY_LEFT, I_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_RECORD, "<data record>");
    r = dataRecord_0(b, l + 1);
    r = r && consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, dataRecord_2(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // dataTypeSpec?
  private static boolean dataRecord_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataRecord_0")) return false;
    dataTypeSpec(b, l + 1);
    return true;
  }

  // dataRecordEntry*
  private static boolean dataRecord_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataRecord_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dataRecordEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dataRecord_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // qid ':' dataValue ','?
  public static boolean dataRecordEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataRecordEntry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_RECORD_ENTRY, "<data record entry>");
    r = qid(b, l + 1);
    r = r && consumeToken(b, I_COLON);
    p = r; // pin = 2
    r = r && report_error_(b, dataValue(b, l + 1));
    r = p && dataRecordEntry_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, dataValueRecover_parser_);
    return r || p;
  }

  // ','?
  private static boolean dataRecordEntry_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataRecordEntry_3")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // dataTypeSpec '(' dataUnspeccedNull ')'
  static boolean dataSpeccedNull(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataSpeccedNull")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dataTypeSpec(b, l + 1);
    r = r && consumeToken(b, I_PAREN_LEFT);
    r = r && dataUnspeccedNull(b, l + 1);
    r = r && consumeToken(b, I_PAREN_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // fqnTypeRef
  static boolean dataTypeSpec(PsiBuilder b, int l) {
    return fqnTypeRef(b, l + 1);
  }

  /* ********************************************************** */
  // 'null'
  static boolean dataUnspeccedNull(PsiBuilder b, int l) {
    return consumeToken(b, I_NULL);
  }

  /* ********************************************************** */
  // dataVar | varValue
  public static boolean dataValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_VALUE, "<data value>");
    r = dataVar(b, l + 1);
    if (!r) r = varValue(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ! ( qid | dataPrimitive | '}' | ')' | '>' | ']' | ',' )
  static boolean dataValueRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataValueRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !dataValueRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // qid | dataPrimitive | '}' | ')' | '>' | ']' | ','
  private static boolean dataValueRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataValueRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    if (!r) r = dataPrimitive(b, l + 1);
    if (!r) r = consumeToken(b, I_CURLY_RIGHT);
    if (!r) r = consumeToken(b, I_PAREN_RIGHT);
    if (!r) r = consumeToken(b, I_ANGLE_RIGHT);
    if (!r) r = consumeToken(b, I_BRACKET_RIGHT);
    if (!r) r = consumeToken(b, I_COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // dataTypeSpec? '<' dataVarEntry* '>'
  public static boolean dataVar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataVar")) return false;
    if (!nextTokenIs(b, "<data var>", I_ANGLE_LEFT, I_ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_VAR, "<data var>");
    r = dataVar_0(b, l + 1);
    r = r && consumeToken(b, I_ANGLE_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, dataVar_2(b, l + 1));
    r = p && consumeToken(b, I_ANGLE_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // dataTypeSpec?
  private static boolean dataVar_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataVar_0")) return false;
    dataTypeSpec(b, l + 1);
    return true;
  }

  // dataVarEntry*
  private static boolean dataVar_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataVar_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dataVarEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dataVar_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // qid ':' varValue ','?
  public static boolean dataVarEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataVarEntry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_DATA_VAR_ENTRY, "<data var entry>");
    r = qid(b, l + 1);
    r = r && consumeToken(b, I_COLON);
    p = r; // pin = 2
    r = r && report_error_(b, varValue(b, l + 1));
    r = p && dataVarEntry_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, dataValueRecover_parser_);
    return r || p;
  }

  // ','?
  private static boolean dataVarEntry_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataVarEntry_3")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // fqnSegment ('.' fqnSegment)*
  public static boolean fqn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fqnSegment(b, l + 1);
    r = r && fqn_1(b, l + 1);
    exit_section_(b, m, I_FQN, r);
    return r;
  }

  // ('.' fqnSegment)*
  private static boolean fqn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fqn_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fqn_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '.' fqnSegment
  private static boolean fqn_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqn_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_DOT);
    r = r && fqnSegment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // qid {
  // //  implements="com.intellij.psi.PsiNameIdentifierOwner"
  // //  methods=[getName setName getNameIdentifier getSchemaFqn getSchemaFqnTypeRef isLast getReference getFqn]
  // }
  public static boolean fqnSegment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqnSegment")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    r = r && fqnSegment_1(b, l + 1);
    exit_section_(b, m, I_FQN_SEGMENT, r);
    return r;
  }

  // {
  // //  implements="com.intellij.psi.PsiNameIdentifierOwner"
  // //  methods=[getName setName getNameIdentifier getSchemaFqn getSchemaFqnTypeRef isLast getReference getFqn]
  // }
  private static boolean fqnSegment_1(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // fqn
  public static boolean fqnTypeRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fqnTypeRef")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_FQN_TYPE_REF, "<type>");
    r = fqn(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // namespaceDeclRecover
  static boolean importRecover(PsiBuilder b, int l) {
    return namespaceDeclRecover(b, l + 1);
  }

  /* ********************************************************** */
  // 'import' fqn
  public static boolean importStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_IMPORT_STATEMENT, "<import statement>");
    r = consumeToken(b, I_IMPORT);
    p = r; // pin = 1
    r = r && fqn(b, l + 1);
    exit_section_(b, l, m, r, p, importRecover_parser_);
    return r || p;
  }

  /* ********************************************************** */
  // importStatement*
  public static boolean imports(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "imports")) return false;
    Marker m = enter_section_(b, l, _NONE_, I_IMPORTS, "<imports>");
    int c = current_position_(b);
    while (true) {
      if (!importStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "imports", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // '{' namespaceBodyPart* '}'
  static boolean namespaceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceBody")) return false;
    if (!nextTokenIs(b, I_CURLY_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, namespaceBody_1(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // namespaceBodyPart*
  private static boolean namespaceBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!namespaceBodyPart(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "namespaceBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // customParam
  static boolean namespaceBodyPart(PsiBuilder b, int l) {
    return customParam(b, l + 1);
  }

  /* ********************************************************** */
  // 'namespace' fqn namespaceBody?
  public static boolean namespaceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceDecl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_NAMESPACE_DECL, "<namespace decl>");
    r = consumeToken(b, I_NAMESPACE);
    p = r; // pin = 1
    r = r && report_error_(b, fqn(b, l + 1));
    r = p && namespaceDecl_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, namespaceDeclRecover_parser_);
    return r || p;
  }

  // namespaceBody?
  private static boolean namespaceDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceDecl_2")) return false;
    namespaceBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ! ('import' | 'namespace' )
  static boolean namespaceDeclRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceDeclRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !namespaceDeclRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // 'import' | 'namespace'
  private static boolean namespaceDeclRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespaceDeclRecover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_IMPORT);
    if (!r) r = consumeToken(b, I_NAMESPACE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'default' ':' varValue
  public static boolean opInputDefaultValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputDefaultValue")) return false;
    if (!nextTokenIs(b, I_DEFAULT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_DEFAULT_VALUE, null);
    r = consumeToken(b, I_DEFAULT);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, I_COLON));
    r = p && varValue(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '+'? qid opInputFieldProjectionBody? opInputVarProjection?
  public static boolean opInputFieldProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjection")) return false;
    if (!nextTokenIs(b, "<op input field projection>", I_PLUS, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_FIELD_PROJECTION, "<op input field projection>");
    r = opInputFieldProjection_0(b, l + 1);
    r = r && qid(b, l + 1);
    r = r && opInputFieldProjection_2(b, l + 1);
    r = r && opInputFieldProjection_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '+'?
  private static boolean opInputFieldProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjection_0")) return false;
    consumeToken(b, I_PLUS);
    return true;
  }

  // opInputFieldProjectionBody?
  private static boolean opInputFieldProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjection_2")) return false;
    opInputFieldProjectionBody(b, l + 1);
    return true;
  }

  // opInputVarProjection?
  private static boolean opInputFieldProjection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjection_3")) return false;
    opInputVarProjection(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' (opInputFieldProjectionBodyPart ','?)* '}'
  public static boolean opInputFieldProjectionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjectionBody")) return false;
    if (!nextTokenIs(b, I_CURLY_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_FIELD_PROJECTION_BODY, null);
    r = consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opInputFieldProjectionBody_1(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputFieldProjectionBodyPart ','?)*
  private static boolean opInputFieldProjectionBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjectionBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputFieldProjectionBody_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputFieldProjectionBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputFieldProjectionBodyPart ','?
  private static boolean opInputFieldProjectionBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjectionBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputFieldProjectionBodyPart(b, l + 1);
    r = r && opInputFieldProjectionBody_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputFieldProjectionBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjectionBody_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // customParam | opInputRequired
  public static boolean opInputFieldProjectionBodyPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputFieldProjectionBodyPart")) return false;
    if (!nextTokenIs(b, "<op input field projection body part>", I_REQURIED, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_FIELD_PROJECTION_BODY_PART, "<op input field projection body part>");
    r = customParam(b, l + 1);
    if (!r) r = opInputRequired(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '[' (opInputKeyProjectionPart ','?)* ']'
  public static boolean opInputKeyProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputKeyProjection")) return false;
    if (!nextTokenIs(b, I_BRACKET_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_KEY_PROJECTION, null);
    r = consumeToken(b, I_BRACKET_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opInputKeyProjection_1(b, l + 1));
    r = p && consumeToken(b, I_BRACKET_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputKeyProjectionPart ','?)*
  private static boolean opInputKeyProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputKeyProjection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputKeyProjection_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputKeyProjection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputKeyProjectionPart ','?
  private static boolean opInputKeyProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputKeyProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputKeyProjectionPart(b, l + 1);
    r = r && opInputKeyProjection_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputKeyProjection_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputKeyProjection_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // 'forbidden' | 'required' | customParam
  public static boolean opInputKeyProjectionPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputKeyProjectionPart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_KEY_PROJECTION_PART, "<op input key projection part>");
    r = consumeToken(b, I_FORBIDDEN);
    if (!r) r = consumeToken(b, I_REQURIED);
    if (!r) r = customParam(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '*' opInputVarProjection
  public static boolean opInputListModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputListModelProjection")) return false;
    if (!nextTokenIs(b, I_STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_LIST_MODEL_PROJECTION, null);
    r = consumeToken(b, I_STAR);
    p = r; // pin = 1
    r = r && opInputVarProjection(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // opInputKeyProjection '*' opInputVarProjection
  public static boolean opInputMapModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMapModelProjection")) return false;
    if (!nextTokenIs(b, I_BRACKET_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_MAP_MODEL_PROJECTION, null);
    r = opInputKeyProjection(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, I_STAR));
    r = p && opInputVarProjection(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '+'? ( opInputRecordModelProjection
  //                                 | opInputListModelProjection
  //                                 | opInputMapModelProjection
  // //                              | opInputEnumModelProjection
  // //                              | opInputPrimitiveModelProjection
  //                                 )? opInputModelProjectionBody?
  public static boolean opInputModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, I_OP_INPUT_MODEL_PROJECTION, "<op input model projection>");
    r = opInputModelProjection_0(b, l + 1);
    r = r && opInputModelProjection_1(b, l + 1);
    r = r && opInputModelProjection_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '+'?
  private static boolean opInputModelProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjection_0")) return false;
    consumeToken(b, I_PLUS);
    return true;
  }

  // ( opInputRecordModelProjection
  //                                 | opInputListModelProjection
  //                                 | opInputMapModelProjection
  // //                              | opInputEnumModelProjection
  // //                              | opInputPrimitiveModelProjection
  //                                 )?
  private static boolean opInputModelProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjection_1")) return false;
    opInputModelProjection_1_0(b, l + 1);
    return true;
  }

  // opInputRecordModelProjection
  //                                 | opInputListModelProjection
  //                                 | opInputMapModelProjection
  private static boolean opInputModelProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputRecordModelProjection(b, l + 1);
    if (!r) r = opInputListModelProjection(b, l + 1);
    if (!r) r = opInputMapModelProjection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // opInputModelProjectionBody?
  private static boolean opInputModelProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjection_2")) return false;
    opInputModelProjectionBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' (opInputModelProjectionBodyPart ','?)* '}'
  public static boolean opInputModelProjectionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjectionBody")) return false;
    if (!nextTokenIs(b, I_CURLY_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_MODEL_PROJECTION_BODY, null);
    r = consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opInputModelProjectionBody_1(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputModelProjectionBodyPart ','?)*
  private static boolean opInputModelProjectionBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjectionBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputModelProjectionBody_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputModelProjectionBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputModelProjectionBodyPart ','?
  private static boolean opInputModelProjectionBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjectionBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputModelProjectionBodyPart(b, l + 1);
    r = r && opInputModelProjectionBody_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputModelProjectionBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjectionBody_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // customParam | opInputDefaultValue | opInputRequired
  public static boolean opInputModelProjectionBodyPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputModelProjectionBodyPart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_MODEL_PROJECTION_BODY_PART, "<op input model projection body part>");
    r = customParam(b, l + 1);
    if (!r) r = opInputDefaultValue(b, l + 1);
    if (!r) r = opInputRequired(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ':' '(' (opInputMultiTagProjectionItem ','?)* ')'
  public static boolean opInputMultiTagProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMultiTagProjection")) return false;
    if (!nextTokenIs(b, I_COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_MULTI_TAG_PROJECTION, null);
    r = consumeToken(b, I_COLON);
    r = r && consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, opInputMultiTagProjection_2(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputMultiTagProjectionItem ','?)*
  private static boolean opInputMultiTagProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMultiTagProjection_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputMultiTagProjection_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputMultiTagProjection_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputMultiTagProjectionItem ','?
  private static boolean opInputMultiTagProjection_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMultiTagProjection_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputMultiTagProjectionItem(b, l + 1);
    r = r && opInputMultiTagProjection_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputMultiTagProjection_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMultiTagProjection_2_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // qid opInputModelProjection
  public static boolean opInputMultiTagProjectionItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputMultiTagProjectionItem")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    r = r && opInputModelProjection(b, l + 1);
    exit_section_(b, m, I_OP_INPUT_MULTI_TAG_PROJECTION_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (opInputFieldProjection ','?)* ')'
  public static boolean opInputRecordModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputRecordModelProjection")) return false;
    if (!nextTokenIs(b, I_PAREN_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_RECORD_MODEL_PROJECTION, null);
    r = consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opInputRecordModelProjection_1(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputFieldProjection ','?)*
  private static boolean opInputRecordModelProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputRecordModelProjection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputRecordModelProjection_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputRecordModelProjection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputFieldProjection ','?
  private static boolean opInputRecordModelProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputRecordModelProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputFieldProjection(b, l + 1);
    r = r && opInputRecordModelProjection_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputRecordModelProjection_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputRecordModelProjection_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // 'required' ':' boolean
  public static boolean opInputRequired(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputRequired")) return false;
    if (!nextTokenIs(b, I_REQURIED)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_REQUIRED, null);
    r = consumeToken(b, I_REQURIED);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, I_COLON));
    r = p && consumeToken(b, I_BOOLEAN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ( ':' qid )? opInputModelProjection
  public static boolean opInputSingleTagProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputSingleTagProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_SINGLE_TAG_PROJECTION, "<op input single tag projection>");
    r = opInputSingleTagProjection_0(b, l + 1);
    r = r && opInputModelProjection(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( ':' qid )?
  private static boolean opInputSingleTagProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputSingleTagProjection_0")) return false;
    opInputSingleTagProjection_0_0(b, l + 1);
    return true;
  }

  // ':' qid
  private static boolean opInputSingleTagProjection_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputSingleTagProjection_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_COLON);
    r = r && qid(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '~' '(' (opInputVarMultiTailItem ','?)* ')'
  public static boolean opInputVarMultiTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarMultiTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_VAR_MULTI_TAIL, null);
    r = consumeToken(b, I_TILDA);
    r = r && consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, opInputVarMultiTail_2(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opInputVarMultiTailItem ','?)*
  private static boolean opInputVarMultiTail_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarMultiTail_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opInputVarMultiTail_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opInputVarMultiTail_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opInputVarMultiTailItem ','?
  private static boolean opInputVarMultiTail_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarMultiTail_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputVarMultiTailItem(b, l + 1);
    r = r && opInputVarMultiTail_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opInputVarMultiTail_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarMultiTail_2_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // fqnTypeRef opInputVarProjection
  public static boolean opInputVarMultiTailItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarMultiTailItem")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fqnTypeRef(b, l + 1);
    r = r && opInputVarProjection(b, l + 1);
    exit_section_(b, m, I_OP_INPUT_VAR_MULTI_TAIL_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // opInputVarSingleTail | opInputVarMultiTail
  public static boolean opInputVarPolymorphicTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarPolymorphicTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputVarSingleTail(b, l + 1);
    if (!r) r = opInputVarMultiTail(b, l + 1);
    exit_section_(b, m, I_OP_INPUT_VAR_POLYMORPHIC_TAIL, r);
    return r;
  }

  /* ********************************************************** */
  // ( opInputMultiTagProjection | opInputSingleTagProjection ) opInputVarPolymorphicTail?
  public static boolean opInputVarProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_INPUT_VAR_PROJECTION, "<op input var projection>");
    r = opInputVarProjection_0(b, l + 1);
    r = r && opInputVarProjection_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // opInputMultiTagProjection | opInputSingleTagProjection
  private static boolean opInputVarProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarProjection_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opInputMultiTagProjection(b, l + 1);
    if (!r) r = opInputSingleTagProjection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // opInputVarPolymorphicTail?
  private static boolean opInputVarProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarProjection_1")) return false;
    opInputVarPolymorphicTail(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '~' fqnTypeRef opInputVarProjection
  public static boolean opInputVarSingleTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opInputVarSingleTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_TILDA);
    r = r && fqnTypeRef(b, l + 1);
    r = r && opInputVarProjection(b, l + 1);
    exit_section_(b, m, I_OP_INPUT_VAR_SINGLE_TAIL, r);
    return r;
  }

  /* ********************************************************** */
  // '+'? qid opOutputFieldProjectionBody? opOutputVarProjection?
  public static boolean opOutputFieldProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjection")) return false;
    if (!nextTokenIs(b, "<op output field projection>", I_PLUS, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_FIELD_PROJECTION, "<op output field projection>");
    r = opOutputFieldProjection_0(b, l + 1);
    r = r && qid(b, l + 1);
    r = r && opOutputFieldProjection_2(b, l + 1);
    r = r && opOutputFieldProjection_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '+'?
  private static boolean opOutputFieldProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjection_0")) return false;
    consumeToken(b, I_PLUS);
    return true;
  }

  // opOutputFieldProjectionBody?
  private static boolean opOutputFieldProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjection_2")) return false;
    opOutputFieldProjectionBody(b, l + 1);
    return true;
  }

  // opOutputVarProjection?
  private static boolean opOutputFieldProjection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjection_3")) return false;
    opOutputVarProjection(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' (opOutputFieldProjectionBodyPart ','?)* '}'
  public static boolean opOutputFieldProjectionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjectionBody")) return false;
    if (!nextTokenIs(b, I_CURLY_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_FIELD_PROJECTION_BODY, null);
    r = consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opOutputFieldProjectionBody_1(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputFieldProjectionBodyPart ','?)*
  private static boolean opOutputFieldProjectionBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjectionBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputFieldProjectionBody_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputFieldProjectionBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputFieldProjectionBodyPart ','?
  private static boolean opOutputFieldProjectionBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjectionBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputFieldProjectionBodyPart(b, l + 1);
    r = r && opOutputFieldProjectionBody_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputFieldProjectionBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjectionBody_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // opParameters | customParam
  public static boolean opOutputFieldProjectionBodyPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputFieldProjectionBodyPart")) return false;
    if (!nextTokenIs(b, "<op output field projection body part>", I_PARAMETERS, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_FIELD_PROJECTION_BODY_PART, "<op output field projection body part>");
    r = opParameters(b, l + 1);
    if (!r) r = customParam(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '[' (opOutputKeyProjectionPart ','?)* ']'
  public static boolean opOutputKeyProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputKeyProjection")) return false;
    if (!nextTokenIs(b, I_BRACKET_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_KEY_PROJECTION, null);
    r = consumeToken(b, I_BRACKET_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opOutputKeyProjection_1(b, l + 1));
    r = p && consumeToken(b, I_BRACKET_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputKeyProjectionPart ','?)*
  private static boolean opOutputKeyProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputKeyProjection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputKeyProjection_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputKeyProjection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputKeyProjectionPart ','?
  private static boolean opOutputKeyProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputKeyProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputKeyProjectionPart(b, l + 1);
    r = r && opOutputKeyProjection_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputKeyProjection_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputKeyProjection_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // 'forbidden' | 'required' | opParameters | customParam
  public static boolean opOutputKeyProjectionPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputKeyProjectionPart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_KEY_PROJECTION_PART, "<op output key projection part>");
    r = consumeToken(b, I_FORBIDDEN);
    if (!r) r = consumeToken(b, I_REQURIED);
    if (!r) r = opParameters(b, l + 1);
    if (!r) r = customParam(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '*' opOutputVarProjection
  public static boolean opOutputListModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputListModelProjection")) return false;
    if (!nextTokenIs(b, I_STAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_LIST_MODEL_PROJECTION, null);
    r = consumeToken(b, I_STAR);
    p = r; // pin = 1
    r = r && opOutputVarProjection(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // opOutputKeyProjection '*' opOutputVarProjection
  public static boolean opOutputMapModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMapModelProjection")) return false;
    if (!nextTokenIs(b, I_BRACKET_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_MAP_MODEL_PROJECTION, null);
    r = opOutputKeyProjection(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, I_STAR));
    r = p && opOutputVarProjection(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '+'? ( opOutputRecordModelProjection
  //                                  | opOutputListModelProjection
  //                                  | opOutputMapModelProjection
  // //                               | opOutputEnumModelProjection
  // //                               | opOutputPrimitiveModelProjection
  //                                  )? opOutputModelProjectionBody?
  public static boolean opOutputModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, I_OP_OUTPUT_MODEL_PROJECTION, "<op output model projection>");
    r = opOutputModelProjection_0(b, l + 1);
    r = r && opOutputModelProjection_1(b, l + 1);
    r = r && opOutputModelProjection_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '+'?
  private static boolean opOutputModelProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjection_0")) return false;
    consumeToken(b, I_PLUS);
    return true;
  }

  // ( opOutputRecordModelProjection
  //                                  | opOutputListModelProjection
  //                                  | opOutputMapModelProjection
  // //                               | opOutputEnumModelProjection
  // //                               | opOutputPrimitiveModelProjection
  //                                  )?
  private static boolean opOutputModelProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjection_1")) return false;
    opOutputModelProjection_1_0(b, l + 1);
    return true;
  }

  // opOutputRecordModelProjection
  //                                  | opOutputListModelProjection
  //                                  | opOutputMapModelProjection
  private static boolean opOutputModelProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputRecordModelProjection(b, l + 1);
    if (!r) r = opOutputListModelProjection(b, l + 1);
    if (!r) r = opOutputMapModelProjection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // opOutputModelProjectionBody?
  private static boolean opOutputModelProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjection_2")) return false;
    opOutputModelProjectionBody(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' (opOutputModelProjectionBodyPart ','?)* '}'
  public static boolean opOutputModelProjectionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjectionBody")) return false;
    if (!nextTokenIs(b, I_CURLY_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_MODEL_PROJECTION_BODY, null);
    r = consumeToken(b, I_CURLY_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opOutputModelProjectionBody_1(b, l + 1));
    r = p && consumeToken(b, I_CURLY_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputModelProjectionBodyPart ','?)*
  private static boolean opOutputModelProjectionBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjectionBody_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputModelProjectionBody_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputModelProjectionBody_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputModelProjectionBodyPart ','?
  private static boolean opOutputModelProjectionBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjectionBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputModelProjectionBodyPart(b, l + 1);
    r = r && opOutputModelProjectionBody_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputModelProjectionBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjectionBody_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // opParameters | customParam
  public static boolean opOutputModelProjectionBodyPart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputModelProjectionBodyPart")) return false;
    if (!nextTokenIs(b, "<op output model projection body part>", I_PARAMETERS, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_MODEL_PROJECTION_BODY_PART, "<op output model projection body part>");
    r = opParameters(b, l + 1);
    if (!r) r = customParam(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ':' '(' (opOutputMultiTagProjectionItem ','?)* ')'
  public static boolean opOutputMultiTagProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMultiTagProjection")) return false;
    if (!nextTokenIs(b, I_COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_MULTI_TAG_PROJECTION, null);
    r = consumeToken(b, I_COLON);
    r = r && consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, opOutputMultiTagProjection_2(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputMultiTagProjectionItem ','?)*
  private static boolean opOutputMultiTagProjection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMultiTagProjection_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputMultiTagProjection_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputMultiTagProjection_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputMultiTagProjectionItem ','?
  private static boolean opOutputMultiTagProjection_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMultiTagProjection_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputMultiTagProjectionItem(b, l + 1);
    r = r && opOutputMultiTagProjection_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputMultiTagProjection_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMultiTagProjection_2_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // qid opOutputModelProjection
  public static boolean opOutputMultiTagProjectionItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputMultiTagProjectionItem")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    r = r && opOutputModelProjection(b, l + 1);
    exit_section_(b, m, I_OP_OUTPUT_MULTI_TAG_PROJECTION_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // '(' (opOutputFieldProjection ','?)* ')'
  public static boolean opOutputRecordModelProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputRecordModelProjection")) return false;
    if (!nextTokenIs(b, I_PAREN_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_RECORD_MODEL_PROJECTION, null);
    r = consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, opOutputRecordModelProjection_1(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputFieldProjection ','?)*
  private static boolean opOutputRecordModelProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputRecordModelProjection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputRecordModelProjection_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputRecordModelProjection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputFieldProjection ','?
  private static boolean opOutputRecordModelProjection_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputRecordModelProjection_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputFieldProjection(b, l + 1);
    r = r && opOutputRecordModelProjection_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputRecordModelProjection_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputRecordModelProjection_1_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // ( ':' qid )? opOutputModelProjection
  public static boolean opOutputSingleTagProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputSingleTagProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_SINGLE_TAG_PROJECTION, "<op output single tag projection>");
    r = opOutputSingleTagProjection_0(b, l + 1);
    r = r && opOutputModelProjection(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( ':' qid )?
  private static boolean opOutputSingleTagProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputSingleTagProjection_0")) return false;
    opOutputSingleTagProjection_0_0(b, l + 1);
    return true;
  }

  // ':' qid
  private static boolean opOutputSingleTagProjection_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputSingleTagProjection_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_COLON);
    r = r && qid(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '~' '(' (opOutputVarMultiTailItem ','?)* ')'
  public static boolean opOutputVarMultiTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarMultiTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_VAR_MULTI_TAIL, null);
    r = consumeToken(b, I_TILDA);
    r = r && consumeToken(b, I_PAREN_LEFT);
    p = r; // pin = 2
    r = r && report_error_(b, opOutputVarMultiTail_2(b, l + 1));
    r = p && consumeToken(b, I_PAREN_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (opOutputVarMultiTailItem ','?)*
  private static boolean opOutputVarMultiTail_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarMultiTail_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opOutputVarMultiTail_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opOutputVarMultiTail_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opOutputVarMultiTailItem ','?
  private static boolean opOutputVarMultiTail_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarMultiTail_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputVarMultiTailItem(b, l + 1);
    r = r && opOutputVarMultiTail_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opOutputVarMultiTail_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarMultiTail_2_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // fqnTypeRef opOutputVarProjection
  public static boolean opOutputVarMultiTailItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarMultiTailItem")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fqnTypeRef(b, l + 1);
    r = r && opOutputVarProjection(b, l + 1);
    exit_section_(b, m, I_OP_OUTPUT_VAR_MULTI_TAIL_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // opOutputVarSingleTail | opOutputVarMultiTail
  public static boolean opOutputVarPolymorphicTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarPolymorphicTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputVarSingleTail(b, l + 1);
    if (!r) r = opOutputVarMultiTail(b, l + 1);
    exit_section_(b, m, I_OP_OUTPUT_VAR_POLYMORPHIC_TAIL, r);
    return r;
  }

  /* ********************************************************** */
  // ( opOutputMultiTagProjection | opOutputSingleTagProjection ) opOutputVarPolymorphicTail?
  public static boolean opOutputVarProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarProjection")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, I_OP_OUTPUT_VAR_PROJECTION, "<op output var projection>");
    r = opOutputVarProjection_0(b, l + 1);
    r = r && opOutputVarProjection_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // opOutputMultiTagProjection | opOutputSingleTagProjection
  private static boolean opOutputVarProjection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarProjection_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opOutputMultiTagProjection(b, l + 1);
    if (!r) r = opOutputSingleTagProjection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // opOutputVarPolymorphicTail?
  private static boolean opOutputVarProjection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarProjection_1")) return false;
    opOutputVarPolymorphicTail(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '~' fqnTypeRef opOutputVarProjection
  public static boolean opOutputVarSingleTail(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opOutputVarSingleTail")) return false;
    if (!nextTokenIs(b, I_TILDA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_TILDA);
    r = r && fqnTypeRef(b, l + 1);
    r = r && opOutputVarProjection(b, l + 1);
    exit_section_(b, m, I_OP_OUTPUT_VAR_SINGLE_TAIL, r);
    return r;
  }

  /* ********************************************************** */
  // qid ':' opInputModelProjection
  public static boolean opParamProjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opParamProjection")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qid(b, l + 1);
    r = r && consumeToken(b, I_COLON);
    r = r && opInputModelProjection(b, l + 1);
    exit_section_(b, m, I_OP_PARAM_PROJECTION, r);
    return r;
  }

  /* ********************************************************** */
  // 'parameters' ':' '{' (opParamProjection ','?)* '}'
  public static boolean opParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opParameters")) return false;
    if (!nextTokenIs(b, I_PARAMETERS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_PARAMETERS);
    r = r && consumeToken(b, I_COLON);
    r = r && consumeToken(b, I_CURLY_LEFT);
    r = r && opParameters_3(b, l + 1);
    r = r && consumeToken(b, I_CURLY_RIGHT);
    exit_section_(b, m, I_OP_PARAMETERS, r);
    return r;
  }

  // (opParamProjection ','?)*
  private static boolean opParameters_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opParameters_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!opParameters_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "opParameters_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // opParamProjection ','?
  private static boolean opParameters_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opParameters_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = opParamProjection(b, l + 1);
    r = r && opParameters_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean opParameters_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opParameters_3_0_1")) return false;
    consumeToken(b, I_COMMA);
    return true;
  }

  /* ********************************************************** */
  // id
  public static boolean qid(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qid")) return false;
    if (!nextTokenIs(b, I_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, I_ID);
    exit_section_(b, m, I_QID, r);
    return r;
  }

  /* ********************************************************** */
  // namespaceDecl imports opOutputVarProjection
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    if (!nextTokenIs(b, I_NAMESPACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = namespaceDecl(b, l + 1);
    r = r && imports(b, l + 1);
    r = r && opOutputVarProjection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // dataRecord | dataMap | dataList | dataPrimitive | dataEnum | dataNull
  public static boolean varValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, I_VAR_VALUE, "<var value>");
    r = dataRecord(b, l + 1);
    if (!r) r = dataMap(b, l + 1);
    if (!r) r = dataList(b, l + 1);
    if (!r) r = dataPrimitive(b, l + 1);
    if (!r) r = dataEnum(b, l + 1);
    if (!r) r = dataNull(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  final static Parser dataValueRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return dataValueRecover(b, l + 1);
    }
  };
  final static Parser importRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return importRecover(b, l + 1);
    }
  };
  final static Parser namespaceDeclRecover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return namespaceDeclRecover(b, l + 1);
    }
  };
}
