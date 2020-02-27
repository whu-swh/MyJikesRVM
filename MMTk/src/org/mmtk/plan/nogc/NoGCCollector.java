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
package org.mmtk.plan.nogc;

import org.mmtk.plan.*;

import org.mmtk.vm.VM;

import org.vmmagic.pragma.*;

/**
 * This class implements <i>per-collector thread</i> behavior and state
 * for the <i>MyGC</i> plan, which simply allocates (without ever collecting
 * until the available space is exhausted.<p>
 *
 * Specifically, this class <i>would</i> define <i>MyGC</i> collection time semantics,
 * however, since this plan never collects, this class consists only of stubs which
 * may be useful as a template for implementing a basic collector.
 *
 * @see NoGC
 * @see NoGCMutator
 * @see CollectorContext
 */
@Uninterruptible
public class NoGCCollector extends ParallelCollector {

  /************************************************************************
   * Instance fields
   */

  /**
   *
   */
  private final NoGCTraceLocal trace = new NoGCTraceLocal(global().trace);
  protected final TraceLocal currentTrace = trace;


  /****************************************************************************
   * Collection
   */

  /**
   * Perform a garbage collection
   */
  @Override
  public final void collect() {
    VM.assertions.fail("GC Triggered in MyGC Plan. Is -X:gc:ignoreSystemGC=true ?");
  }

  @Inline
  @Override
  public final void collectionPhase(short phaseId, boolean primary) {
    VM.assertions.fail("GC Triggered in MyGC Plan.");
    /*
    if (phaseId == MyGC.PREPARE) {
    }

    if (phaseId == MyGC.CLOSURE) {
    }

    if (phaseId == MyGC.RELEASE) {
    }

    super.collectionPhase(phaseId, primary);
    */
  }

  /****************************************************************************
   * Miscellaneous
   */

  /** @return The active global plan as a <code>MyGC</code> instance. */
  @Inline
  private static NoGC global() {
    return (NoGC) VM.activePlan.global();
  }

  @Override
  public final TraceLocal getCurrentTrace() {
    return currentTrace;
  }
}
