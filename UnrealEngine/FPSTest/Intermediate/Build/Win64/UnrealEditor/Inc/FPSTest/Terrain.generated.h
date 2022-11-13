// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/ObjectMacros.h"
#include "UObject/ScriptMacros.h"

PRAGMA_DISABLE_DEPRECATION_WARNINGS
#ifdef FPSTEST_Terrain_generated_h
#error "Terrain.generated.h already included, missing '#pragma once' in Terrain.h"
#endif
#define FPSTEST_Terrain_generated_h

#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_SPARSE_DATA
#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_RPC_WRAPPERS
#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_RPC_WRAPPERS_NO_PURE_DECLS
#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_INCLASS_NO_PURE_DECLS \
private: \
	static void StaticRegisterNativesATerrain(); \
	friend struct Z_Construct_UClass_ATerrain_Statics; \
public: \
	DECLARE_CLASS(ATerrain, AActor, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(ATerrain)


#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_INCLASS \
private: \
	static void StaticRegisterNativesATerrain(); \
	friend struct Z_Construct_UClass_ATerrain_Statics; \
public: \
	DECLARE_CLASS(ATerrain, AActor, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(ATerrain)


#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_STANDARD_CONSTRUCTORS \
	/** Standard constructor, called after all reflected properties have been initialized */ \
	NO_API ATerrain(const FObjectInitializer& ObjectInitializer); \
	DEFINE_DEFAULT_OBJECT_INITIALIZER_CONSTRUCTOR_CALL(ATerrain) \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, ATerrain); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(ATerrain); \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API ATerrain(ATerrain&&); \
	NO_API ATerrain(const ATerrain&); \
public:


#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_ENHANCED_CONSTRUCTORS \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API ATerrain(ATerrain&&); \
	NO_API ATerrain(const ATerrain&); \
public: \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, ATerrain); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(ATerrain); \
	DEFINE_DEFAULT_CONSTRUCTOR_CALL(ATerrain)


#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_12_PROLOG
#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_GENERATED_BODY_LEGACY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_RPC_WRAPPERS \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_INCLASS \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_STANDARD_CONSTRUCTORS \
public: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


#define FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_GENERATED_BODY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_RPC_WRAPPERS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_INCLASS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_Public_Terrain_h_15_ENHANCED_CONSTRUCTORS \
private: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


template<> FPSTEST_API UClass* StaticClass<class ATerrain>();

#undef CURRENT_FILE_ID
#define CURRENT_FILE_ID FID_FPSTest_Source_FPSTest_Public_Terrain_h


PRAGMA_ENABLE_DEPRECATION_WARNINGS
