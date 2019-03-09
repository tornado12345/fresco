/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.Set;
import javax.annotation.Nullable;

public class PipelineDraweeControllerBuilderSupplier implements
    Supplier<PipelineDraweeControllerBuilder> {

  private final Context mContext;
  private final ImagePipeline mImagePipeline;
  private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;
  private final Set<ControllerListener> mBoundControllerListeners;

  public PipelineDraweeControllerBuilderSupplier(Context context) {
    this(context, null);
  }

  public PipelineDraweeControllerBuilderSupplier(
      Context context,
      @Nullable DraweeConfig draweeConfig) {
    this(context, ImagePipelineFactory.getInstance(), draweeConfig);
  }

  public PipelineDraweeControllerBuilderSupplier(
      Context context,
      ImagePipelineFactory imagePipelineFactory,
      @Nullable DraweeConfig draweeConfig) {
    this(context, imagePipelineFactory, null, draweeConfig);
  }

  public PipelineDraweeControllerBuilderSupplier(
      Context context,
      ImagePipelineFactory imagePipelineFactory,
      Set<ControllerListener> boundControllerListeners,
      @Nullable DraweeConfig draweeConfig) {
    mContext = context;
    mImagePipeline = imagePipelineFactory.getImagePipeline();

    if (draweeConfig != null && draweeConfig.getPipelineDraweeControllerFactory() != null) {
      mPipelineDraweeControllerFactory = draweeConfig.getPipelineDraweeControllerFactory();
    } else {
      mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
    }
    mPipelineDraweeControllerFactory.init(
        context.getResources(),
        DeferredReleaser.getInstance(),
        imagePipelineFactory.getAnimatedDrawableFactory(context),
        UiThreadImmediateExecutorService.getInstance(),
        mImagePipeline.getBitmapMemoryCache(),
        draweeConfig != null
            ? draweeConfig.getCustomDrawableFactories()
            : null,
        draweeConfig != null
            ? draweeConfig.getDebugOverlayEnabledSupplier()
            : null);
    mBoundControllerListeners = boundControllerListeners;
  }

  @Override
  public PipelineDraweeControllerBuilder get() {
    return new PipelineDraweeControllerBuilder(
        mContext,
        mPipelineDraweeControllerFactory,
        mImagePipeline,
        mBoundControllerListeners);
  }
}
