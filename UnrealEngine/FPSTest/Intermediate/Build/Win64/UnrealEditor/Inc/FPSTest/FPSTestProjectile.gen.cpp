// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
#include "FPSTest/FPSTestProjectile.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeFPSTestProjectile() {}
// Cross Module References
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestProjectile_NoRegister();
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestProjectile();
	ENGINE_API UClass* Z_Construct_UClass_AActor();
	UPackage* Z_Construct_UPackage__Script_FPSTest();
	ENGINE_API UClass* Z_Construct_UClass_UPrimitiveComponent_NoRegister();
	ENGINE_API UClass* Z_Construct_UClass_AActor_NoRegister();
	COREUOBJECT_API UScriptStruct* Z_Construct_UScriptStruct_FVector();
	ENGINE_API UScriptStruct* Z_Construct_UScriptStruct_FHitResult();
	ENGINE_API UClass* Z_Construct_UClass_USphereComponent_NoRegister();
	ENGINE_API UClass* Z_Construct_UClass_UProjectileMovementComponent_NoRegister();
// End Cross Module References
	DEFINE_FUNCTION(AFPSTestProjectile::execOnHit)
	{
		P_GET_OBJECT(UPrimitiveComponent,Z_Param_HitComp);
		P_GET_OBJECT(AActor,Z_Param_OtherActor);
		P_GET_OBJECT(UPrimitiveComponent,Z_Param_OtherComp);
		P_GET_STRUCT(FVector,Z_Param_NormalImpulse);
		P_GET_STRUCT_REF(FHitResult,Z_Param_Out_Hit);
		P_FINISH;
		P_NATIVE_BEGIN;
		P_THIS->OnHit(Z_Param_HitComp,Z_Param_OtherActor,Z_Param_OtherComp,Z_Param_NormalImpulse,Z_Param_Out_Hit);
		P_NATIVE_END;
	}
	void AFPSTestProjectile::StaticRegisterNativesAFPSTestProjectile()
	{
		UClass* Class = AFPSTestProjectile::StaticClass();
		static const FNameNativePtrPair Funcs[] = {
			{ "OnHit", &AFPSTestProjectile::execOnHit },
		};
		FNativeFunctionRegistrar::RegisterFunctions(Class, Funcs, UE_ARRAY_COUNT(Funcs));
	}
	struct Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics
	{
		struct FPSTestProjectile_eventOnHit_Parms
		{
			UPrimitiveComponent* HitComp;
			AActor* OtherActor;
			UPrimitiveComponent* OtherComp;
			FVector NormalImpulse;
			FHitResult Hit;
		};
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_HitComp_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_HitComp;
		static const UECodeGen_Private::FObjectPropertyParams NewProp_OtherActor;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_OtherComp_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_OtherComp;
		static const UECodeGen_Private::FStructPropertyParams NewProp_NormalImpulse;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_Hit_MetaData[];
#endif
		static const UECodeGen_Private::FStructPropertyParams NewProp_Hit;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Function_MetaDataParams[];
#endif
		static const UECodeGen_Private::FFunctionParams FuncParams;
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_HitComp_MetaData[] = {
		{ "EditInline", "true" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_HitComp = { "HitComp", nullptr, (EPropertyFlags)0x0010000000080080, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(FPSTestProjectile_eventOnHit_Parms, HitComp), Z_Construct_UClass_UPrimitiveComponent_NoRegister, METADATA_PARAMS(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_HitComp_MetaData, UE_ARRAY_COUNT(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_HitComp_MetaData)) };
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherActor = { "OtherActor", nullptr, (EPropertyFlags)0x0010000000000080, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(FPSTestProjectile_eventOnHit_Parms, OtherActor), Z_Construct_UClass_AActor_NoRegister, METADATA_PARAMS(nullptr, 0) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherComp_MetaData[] = {
		{ "EditInline", "true" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherComp = { "OtherComp", nullptr, (EPropertyFlags)0x0010000000080080, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(FPSTestProjectile_eventOnHit_Parms, OtherComp), Z_Construct_UClass_UPrimitiveComponent_NoRegister, METADATA_PARAMS(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherComp_MetaData, UE_ARRAY_COUNT(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherComp_MetaData)) };
	const UECodeGen_Private::FStructPropertyParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_NormalImpulse = { "NormalImpulse", nullptr, (EPropertyFlags)0x0010000000000080, UECodeGen_Private::EPropertyGenFlags::Struct, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(FPSTestProjectile_eventOnHit_Parms, NormalImpulse), Z_Construct_UScriptStruct_FVector, METADATA_PARAMS(nullptr, 0) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_Hit_MetaData[] = {
		{ "NativeConst", "" },
	};
#endif
	const UECodeGen_Private::FStructPropertyParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_Hit = { "Hit", nullptr, (EPropertyFlags)0x0010008008000182, UECodeGen_Private::EPropertyGenFlags::Struct, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(FPSTestProjectile_eventOnHit_Parms, Hit), Z_Construct_UScriptStruct_FHitResult, METADATA_PARAMS(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_Hit_MetaData, UE_ARRAY_COUNT(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_Hit_MetaData)) }; // 1416937132
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_HitComp,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherActor,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_OtherComp,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_NormalImpulse,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::NewProp_Hit,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::Function_MetaDataParams[] = {
		{ "Comment", "/** called when projectile hits something */" },
		{ "ModuleRelativePath", "FPSTestProjectile.h" },
		{ "ToolTip", "called when projectile hits something" },
	};
#endif
	const UECodeGen_Private::FFunctionParams Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::FuncParams = { (UObject*(*)())Z_Construct_UClass_AFPSTestProjectile, nullptr, "OnHit", nullptr, nullptr, sizeof(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::FPSTestProjectile_eventOnHit_Parms), Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::PropPointers, UE_ARRAY_COUNT(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::PropPointers), RF_Public|RF_Transient|RF_MarkAsNative, (EFunctionFlags)0x00C20401, 0, 0, METADATA_PARAMS(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::Function_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::Function_MetaDataParams)) };
	UFunction* Z_Construct_UFunction_AFPSTestProjectile_OnHit()
	{
		static UFunction* ReturnFunction = nullptr;
		if (!ReturnFunction)
		{
			UECodeGen_Private::ConstructUFunction(&ReturnFunction, Z_Construct_UFunction_AFPSTestProjectile_OnHit_Statics::FuncParams);
		}
		return ReturnFunction;
	}
	IMPLEMENT_CLASS_NO_AUTO_REGISTRATION(AFPSTestProjectile);
	UClass* Z_Construct_UClass_AFPSTestProjectile_NoRegister()
	{
		return AFPSTestProjectile::StaticClass();
	}
	struct Z_Construct_UClass_AFPSTestProjectile_Statics
	{
		static UObject* (*const DependentSingletons[])();
		static const FClassFunctionLinkInfo FuncInfo[];
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Class_MetaDataParams[];
#endif
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_CollisionComp_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_CollisionComp;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_ProjectileMovement_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_ProjectileMovement;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
		static const FCppClassTypeInfoStatic StaticCppClassTypeInfo;
		static const UECodeGen_Private::FClassParams ClassParams;
	};
	UObject* (*const Z_Construct_UClass_AFPSTestProjectile_Statics::DependentSingletons[])() = {
		(UObject* (*)())Z_Construct_UClass_AActor,
		(UObject* (*)())Z_Construct_UPackage__Script_FPSTest,
	};
	const FClassFunctionLinkInfo Z_Construct_UClass_AFPSTestProjectile_Statics::FuncInfo[] = {
		{ &Z_Construct_UFunction_AFPSTestProjectile_OnHit, "OnHit" }, // 1121860730
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_AFPSTestProjectile_Statics::Class_MetaDataParams[] = {
		{ "IncludePath", "FPSTestProjectile.h" },
		{ "ModuleRelativePath", "FPSTestProjectile.h" },
	};
#endif
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_CollisionComp_MetaData[] = {
		{ "Category", "Projectile" },
		{ "Comment", "/** Sphere collision component */" },
		{ "EditInline", "true" },
		{ "ModuleRelativePath", "FPSTestProjectile.h" },
		{ "ToolTip", "Sphere collision component" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_CollisionComp = { "CollisionComp", nullptr, (EPropertyFlags)0x00400000000b0009, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(AFPSTestProjectile, CollisionComp), Z_Construct_UClass_USphereComponent_NoRegister, METADATA_PARAMS(Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_CollisionComp_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_CollisionComp_MetaData)) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_ProjectileMovement_MetaData[] = {
		{ "AllowPrivateAccess", "true" },
		{ "Category", "Movement" },
		{ "Comment", "/** Projectile movement component */" },
		{ "EditInline", "true" },
		{ "ModuleRelativePath", "FPSTestProjectile.h" },
		{ "ToolTip", "Projectile movement component" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_ProjectileMovement = { "ProjectileMovement", nullptr, (EPropertyFlags)0x00400000000a001d, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(AFPSTestProjectile, ProjectileMovement), Z_Construct_UClass_UProjectileMovementComponent_NoRegister, METADATA_PARAMS(Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_ProjectileMovement_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_ProjectileMovement_MetaData)) };
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UClass_AFPSTestProjectile_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_CollisionComp,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_AFPSTestProjectile_Statics::NewProp_ProjectileMovement,
	};
	const FCppClassTypeInfoStatic Z_Construct_UClass_AFPSTestProjectile_Statics::StaticCppClassTypeInfo = {
		TCppClassTypeTraits<AFPSTestProjectile>::IsAbstract,
	};
	const UECodeGen_Private::FClassParams Z_Construct_UClass_AFPSTestProjectile_Statics::ClassParams = {
		&AFPSTestProjectile::StaticClass,
		"Game",
		&StaticCppClassTypeInfo,
		DependentSingletons,
		FuncInfo,
		Z_Construct_UClass_AFPSTestProjectile_Statics::PropPointers,
		nullptr,
		UE_ARRAY_COUNT(DependentSingletons),
		UE_ARRAY_COUNT(FuncInfo),
		UE_ARRAY_COUNT(Z_Construct_UClass_AFPSTestProjectile_Statics::PropPointers),
		0,
		0x008000A4u,
		METADATA_PARAMS(Z_Construct_UClass_AFPSTestProjectile_Statics::Class_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UClass_AFPSTestProjectile_Statics::Class_MetaDataParams))
	};
	UClass* Z_Construct_UClass_AFPSTestProjectile()
	{
		if (!Z_Registration_Info_UClass_AFPSTestProjectile.OuterSingleton)
		{
			UECodeGen_Private::ConstructUClass(Z_Registration_Info_UClass_AFPSTestProjectile.OuterSingleton, Z_Construct_UClass_AFPSTestProjectile_Statics::ClassParams);
		}
		return Z_Registration_Info_UClass_AFPSTestProjectile.OuterSingleton;
	}
	template<> FPSTEST_API UClass* StaticClass<AFPSTestProjectile>()
	{
		return AFPSTestProjectile::StaticClass();
	}
	DEFINE_VTABLE_PTR_HELPER_CTOR(AFPSTestProjectile);
	struct Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_Statics
	{
		static const FClassRegisterCompiledInInfo ClassInfo[];
	};
	const FClassRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_Statics::ClassInfo[] = {
		{ Z_Construct_UClass_AFPSTestProjectile, AFPSTestProjectile::StaticClass, TEXT("AFPSTestProjectile"), &Z_Registration_Info_UClass_AFPSTestProjectile, CONSTRUCT_RELOAD_VERSION_INFO(FClassReloadVersionInfo, sizeof(AFPSTestProjectile), 1307771305U) },
	};
	static FRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_3976544467(TEXT("/Script/FPSTest"),
		Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_Statics::ClassInfo, UE_ARRAY_COUNT(Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestProjectile_h_Statics::ClassInfo),
		nullptr, 0,
		nullptr, 0);
PRAGMA_ENABLE_DEPRECATION_WARNINGS
