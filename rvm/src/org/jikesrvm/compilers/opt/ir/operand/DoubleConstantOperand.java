/*
 *  This file is part of the Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License. You
 *  may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */
package org.jikesrvm.compilers.opt.ir.operand;

import org.jikesrvm.classloader.TypeReference;
import org.jikesrvm.runtime.Entrypoints;
import org.vmmagic.unboxed.Offset;

/**
 * Represents a constant double operand.
 *
 * @see Operand
 */
public final class DoubleConstantOperand extends ConstantOperand {

  /**
   * Value of this operand.
   */
  public double value;

  /**
   * Offset in JTOC where this double constant lives. (0 for constants
   * obtained from constant folding)
   */
  public Offset offset;

  /**
   * Constructs a new double constant operand with the specified value.
   *
   * @param v value
   */
  public DoubleConstantOperand(double v) {
    value = v;
    if (v == 0.) {
      offset = Entrypoints.zeroDoubleField.getOffset();
    } else if (v == 1.) {
      offset = Entrypoints.oneDoubleField.getOffset();
    } else {
      offset = Offset.zero();
    }
  }

  /**
   * Constructs a new double constant operand with the specified value and JTOC offset.
   *
   * @param v value
   * @param i offset in the jtoc
   */
  public DoubleConstantOperand(double v, Offset i) {
    value = v;
    offset = i;
  }

  @Override
  public Operand copy() {
    return new DoubleConstantOperand(value, offset);
  }

  /**
   * @return {@link TypeReference#Double}
   */
  @Override
  public TypeReference getType() {
    return TypeReference.Double;
  }

  /**
   * @return <code>true</code>
   */
  @Override
  public boolean isDouble() {
    return true;
  }

  @Override
  public boolean similar(Operand op) {
    return (op instanceof DoubleConstantOperand) && (value == ((DoubleConstantOperand) op).value);
  }

  /**
   * Returns the string representation of this operand.
   *
   * @return a string representation of this operand.
   */
  @Override
  public String toString() {
    return Double.toString(value) + "D";
  }

}
