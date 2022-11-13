// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/ObjectMacros.h"
#include "UObject/ScriptMacros.h"

PRAGMA_DISABLE_DEPRECATION_WARNINGS
class UPrimitiveComponent;
class AActor;
struct FHitResult;
#ifdef FPSTEST_FPSTestProjectile_generated_h
#error "FPSTestProjectile.generated.h already included, missing '#pragma once' in FPSTestProjectile.h"
#endif
#define FPSTEST_FPSTestProjectile_generated_h

#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_SPARSE_DATA
#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_RPC_WRAPPERS \
 \
	DECLARE_FUNCTION(execOnHit);


#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_RPC_WRAPPERS_NO_PURE_DECLS \
 \
	DECLARE_FUNCTION(execOnHit);


#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_INCLASS_NO_PURE_DECLS \
private: \
	static void StaticRegisterNativesAFPSTestProjectile(); \
	friend struct Z_Construct_UClass_AFPSTestProjectile_Statics; \
public: \
	DECLARE_CLASS(AFPSTestProjectile, AActor, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(AFPSTestProjectile) \
	static const TCHAR* StaticConfigName() {return TEXT("Game");} \



#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_INCLASS \
private: \
	static void StaticRegisterNativesAFPSTestProjectile(); \
	friend struct Z_Construct_UClass_AFPSTestProjectile_Statics; \
public: \
	DECLARE_CLASS(AFPSTestProjectile, AActor, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(AFPSTestProjectile) \
	static const TCHAR* StaticConfigName() {return TEXT("Game");} \



#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_STANDARD_CONSTRUCTORS \
	/** Standard constructor, called after all reflected properties have been initialized */ \
	NO_API AFPSTestProjectile(const FObjectInitializer& ObjectInitializer); \
	DEFINE_DEFAULT_OBJECT_INITIALIZER_CONSTRUCTOR_CALL(AFPSTestProjectile) \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, AFPSTestProjectile); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(AFPSTestProjectile); \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API AFPSTestProjectile(AFPSTestProjectile&&); \
	NO_API AFPSTestProjectile(const AFPSTestProjectile&); \
public:


#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_ENHANCED_CONSTRUCTORS \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API AFPSTestProjectile(AFPSTestProjectile&&); \
	NO_API AFPSTestProjectile(const AFPSTestProjectile&); \
public: \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, AFPSTestProjectile); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(AFPSTestProjectile); \
	DEFINE_DEFAULT_CONSTRUCTOR_CALL(AFPSTestProjectile)


#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_12_PROLOG
#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_GENERATED_BODY_LEGACY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_RPC_WRAPPERS \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_INCLASS \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_STANDARD_CONSTRUCTORS \
public: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


#define FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_GENERATED_BODY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_RPC_WRAPPERS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_INCLASS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_15_ENHANCED_CONSTRUCTORS \
private: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


template<> FPSTEST_API UClass* StaticClass<class AFPSTestProjectile>();

#undef CURRENT_FILE_ID
#define CURRENT_FILE_ID FID_FPSTest_Source_FPSTest_FPSTestProjectile_h


PRAGMA_ENABLE_DEPRECATION_WARNINGS
