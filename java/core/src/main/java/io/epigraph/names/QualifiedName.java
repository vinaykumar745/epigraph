/* Created by yegor on 7/22/16. */

package io.epigraph.names;

import org.jetbrains.annotations.Nullable;

import java.util.AbstractList;

public class QualifiedName extends AbstractList<String> {

  @Nullable
  public final NamespaceName namespaceName;

  public final String localName;

  public QualifiedName(@Nullable NamespaceName namespaceName, String localName) {
    this.namespaceName = namespaceName;
    this.localName = localName;
  }

  @Override
  public String get(int index) {
    if (index < 0 || size <= index) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    return index == size - 1 ? localName : namespaceName.get(index);
  }

  private int size = 0;

  @Override
  public int size() {
    switch (size) {
      case 0:
        size = namespaceName == null ? 1 : namespaceName.size() + 1;
      default:
        return size;
    }
  }

  @Override
  public String toString() {
    return String.join(".", this);
  }

}