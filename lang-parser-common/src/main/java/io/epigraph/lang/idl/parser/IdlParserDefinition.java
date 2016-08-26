package io.epigraph.lang.idl.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.epigraph.lang.idl.parser.psi.IdlFile;
import io.epigraph.lang.lexer.EpigraphFlexAdapter;
import io.epigraph.lang.parser.psi.stubs.EpigraphStubElementTypes;
import org.jetbrains.annotations.NotNull;

import static io.epigraph.lang.lexer.EpigraphElementTypes.*;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class IdlParserDefinition implements ParserDefinition {
//  public static final SchemaParserDefinition INSTANCE = new SchemaParserDefinition();
  public final static TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);
  public final static TokenSet IDENTIFIERS = TokenSet.create(E_ID);
  public final static TokenSet COMMENTS = TokenSet.create(E_COMMENT, E_BLOCK_COMMENT);
  public final static TokenSet CURLY_BRACES = TokenSet.create(E_CURLY_LEFT, E_CURLY_RIGHT);
  public final static TokenSet KEYWORDS = TokenSet.create(
      E_NAMESPACE,
      E_IMPORT,
      E_DEFAULT,
      E_NULL // or is it a LITERAL?
  );
  public final static TokenSet STRING_LITERALS = TokenSet.create(E_STRING);
  public final static TokenSet LITERALS = TokenSet.andSet(STRING_LITERALS, TokenSet.create(E_NUMBER));
  public final static TokenSet TYPE_KINDS = TokenSet.create(E_VARTYPE, E_RECORD, E_MAP, E_LIST, E_ENUM,
      E_STRING_T, E_INTEGER_T, E_LONG_T, E_DOUBLE_T, E_BOOLEAN_T);

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return EpigraphFlexAdapter.newInstance();
  }

  @Override
  public PsiParser createParser(Project project) {
    return new IdlParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return EpigraphStubElementTypes.IDL_FILE;
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return WHITESPACES;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return STRING_LITERALS;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return Factory.createElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new IdlFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY; // TODO refine
  }

  //

  public static boolean isKeyword(@NotNull String name) {
    EpigraphFlexAdapter lexer = EpigraphFlexAdapter.newInstance();
    lexer.start(name);
    return IdlParserDefinition.KEYWORDS.contains(lexer.getTokenType()) && lexer.getTokenEnd() == name.length();
  }

  public static boolean isIdentifier(@NotNull String name) {
    EpigraphFlexAdapter lexer = EpigraphFlexAdapter.newInstance();
    lexer.start(name);
    return IdlParserDefinition.IDENTIFIERS.contains(lexer.getTokenType()) && lexer.getTokenEnd() == name.length();
  }
}
