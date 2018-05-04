/*
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.facebook.drawee.backends.pipeline.info;

import static com.facebook.drawee.backends.pipeline.info.ImageLoadStatus.AVAILABLE;
import static com.facebook.drawee.backends.pipeline.info.ImageLoadStatus.CANCELED;
import static com.facebook.drawee.backends.pipeline.info.ImageLoadStatus.ERROR;
import static com.facebook.drawee.backends.pipeline.info.ImageLoadStatus.ORIGIN_AVAILABLE;
import static com.facebook.drawee.backends.pipeline.info.ImageLoadStatus.REQUESTED;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;

@Retention(SOURCE)
@IntDef({
  REQUESTED,
  ORIGIN_AVAILABLE,
  AVAILABLE,
  CANCELED,
  ERROR,
})
public @interface ImageLoadStatus {

  int REQUESTED = 0;
  int ORIGIN_AVAILABLE = 1;
  int AVAILABLE = 2;
  int CANCELED = 3;
  int ERROR = 4;
}
