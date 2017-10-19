/*
 * Copyright 2017 Sumo Logic
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

// This is a generated file. Not intended for manual editing.
package ws.epigraph.url.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ws.epigraph.url.lexer.UrlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ws.epigraph.url.parser.psi.*;

public class UrlReqEntityPolymorphicTailImpl extends ASTWrapperPsiElement implements UrlReqEntityPolymorphicTail {

  public UrlReqEntityPolymorphicTailImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitReqEntityPolymorphicTail(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UrlReqEntityMultiTail getReqEntityMultiTail() {
    return findChildByClass(UrlReqEntityMultiTail.class);
  }

  @Override
  @Nullable
  public UrlReqEntitySingleTail getReqEntitySingleTail() {
    return findChildByClass(UrlReqEntitySingleTail.class);
  }

  @Override
  @NotNull
  public PsiElement getColon() {
    return findNotNullChildByType(U_COLON);
  }

  @Override
  @NotNull
  public PsiElement getTilda() {
    return findNotNullChildByType(U_TILDA);
  }

}