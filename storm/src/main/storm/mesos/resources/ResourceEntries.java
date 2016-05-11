/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package storm.mesos.resources;

public class ResourceEntries {
  public static final class ScalarResourceEntry implements ResourceEntry<Double> {
    private ReservationType reservationType;
    private Double value;

    public ReservationType getReservationType() {
      return reservationType;
    }

    public ScalarResourceEntry(Double value) {
      this.value = value;
    }

    public ScalarResourceEntry(ReservationType reservationType, Double value) {
      this.reservationType = reservationType;
      this.value = value;
    }

    public Double getValue() {
      return value;
    }

    @Override
    public ScalarResourceEntry add(ResourceEntry<Double> resourceEntry) {
      ScalarResourceEntry scalarResourceEntry = (ScalarResourceEntry) resourceEntry;
      this.value += scalarResourceEntry.getValue();
      return this;
    }

    @Override
    public ScalarResourceEntry remove(ResourceEntry<Double> resourceEntry) {
      ScalarResourceEntry scalarResourceEntry = (ScalarResourceEntry) resourceEntry;
      this.value -= scalarResourceEntry.getValue();
      return this;
    }
  }

  public static final class RangeResourceEntry implements ResourceEntry<Long> {
    private ReservationType reservationType;
    private Long begin;
    private Long end;

    public RangeResourceEntry(Long begin, Long end) {
      this.reservationType = reservationType;
      this.begin = begin;
      this.end = end;
    }

    public RangeResourceEntry(ReservationType reservationType, Long begin, Long end) {
      this.reservationType = reservationType;
      this.begin = begin;
      this.end = end;
    }

    public Long getBegin() {
      return begin;
    }

    public Long getEnd() {
      return end;
    }

    public ReservationType getReservationType() {
      return reservationType;
    }

    @Override
    public RangeResourceEntry add(ResourceEntry<Long> resourceEntry) {
      RangeResourceEntry rangeResourceEntry = (RangeResourceEntry) resourceEntry;
      if (this.begin < rangeResourceEntry.getBegin()) {
        this.begin = rangeResourceEntry.getBegin();
      }
      if (this.end > rangeResourceEntry.getBegin()) {
        this.end = rangeResourceEntry.getBegin();
      }
      return this;
    }

    @Override
    public RangeResourceEntry remove(ResourceEntry<Long> resourceEntry) {
      RangeResourceEntry rangeResourceEntry = (RangeResourceEntry) resourceEntry;
      if (this.begin > rangeResourceEntry.getBegin()) {
        this.begin = rangeResourceEntry.getBegin();
      }

      if (this.end < rangeResourceEntry.getEnd()) {
        this.end = rangeResourceEntry.getEnd();
      }
      return this;
    }
  }
}